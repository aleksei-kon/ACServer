package by.aleksei.acs.service

import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.entities.AdItem
import by.aleksei.acs.entities.AdvertInfo
import by.aleksei.acs.entities.NewDetailsModel
import by.aleksei.acs.entities.OperationStatus
import by.aleksei.acs.entities.db.Advert
import by.aleksei.acs.entities.db.BookmarkId
import by.aleksei.acs.entities.db.Photo
import by.aleksei.acs.repository.AccountRepository
import by.aleksei.acs.repository.AdvertRepository
import by.aleksei.acs.repository.getAccountByToken
import by.aleksei.acs.util.ServiceResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AdvertService(
        private val accountRepository: AccountRepository,
        private val advertRepository: AdvertRepository) {

    fun add(token: String, advertInfo: NewDetailsModel): ServiceResponse<Boolean> {
        val account = accountRepository.getAccountByToken(token)
        val advert = Advert(
                title = advertInfo.title,
                price = advertInfo.price,
                userId = account?.id ?: -1,
                isShown = false,
                location = advertInfo.location,
                synopsis = advertInfo.synopsis,
                phone = advertInfo.phone,
                photos = advertInfo.photos.map { Photo(photo = it) }.toMutableList()
        )

        advertRepository.save(advert)

        return ServiceResponse.Success(true)
    }

    fun addRemoveToBookmark(token: String, bookmarkId: Int): ServiceResponse<Boolean> {
        val account = accountRepository.getAccountByToken(token)
        account?.bookmarkIds?.add(BookmarkId(bookmarkId))
        account?.let { accountRepository.save(it) }

        return ServiceResponse.Success(true)
    }

    fun showHideAd(token: String, advertId: Int): ServiceResponse<Boolean> {
        val account = accountRepository.getAccountByToken(token)

        return if (account?.isAdmin == true) {
            val advert = advertRepository.findByIdOrNull(advertId)
            val newAdvert = advert?.copy(isShown = !advert.isShown)
            newAdvert?.let { advertRepository.save(it) }

            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Error("showHide function only for admin users")
        }
    }

    fun update(token: String, advertInfo: AdvertInfo): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
    }

    fun delete(token: String, advertId: String): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
    }

    fun get(pageNumber: Int, username: String, sortType: Int): ServiceResponse<List<Advert>> {
        return ServiceResponse.Success(emptyList())
    }

    fun getLastAds(pageNumber: Int): ServiceResponse<List<AdItem>> {
        val list = advertRepository.findAll()
                .sortedByDescending { it.date }
                .filter { it.isShown }
                .map {
                    AdItem(
                            id = it.id.toString(),
                            photoUrl = if (it.photos.isNotEmpty()) {
                                it.photos[0].photo
                            } else {
                                EMPTY
                            },
                            date = it.date,
                            title = it.title,
                            price = it.price,
                            place = it.location,
                            views = it.views
                    )
                }

        return ServiceResponse.Success(getPageSublist(list, pageNumber))
    }

    fun getMyAds(token: String, pageNumber: Int): ServiceResponse<List<AdItem>> {
        val account = accountRepository.getAccountByToken(token)
        val list = advertRepository.findAll()
                .sortedByDescending { it.date }
                .filter { it.userId == account?.id ?: EMPTY }
                .map {
                    AdItem(
                            id = it.id.toString(),
                            photoUrl = if (it.photos.isNotEmpty()) {
                                it.photos[0].photo
                            } else {
                                EMPTY
                            },
                            date = it.date,
                            title = it.title,
                            price = it.price,
                            place = it.location,
                            views = it.views
                    )
                }

        return ServiceResponse.Success(getPageSublist(list, pageNumber))
    }

    fun getBookmarks(token: String, pageNumber: Int): ServiceResponse<List<AdItem>> {
        val account = accountRepository.getAccountByToken(token)

        val userBookmarkIds = account?.bookmarkIds?.map { it.bookmarkId } ?: emptyList()

        val list = advertRepository.findAll()
                .sortedByDescending { it.date }
                .filter { userBookmarkIds.contains(it.id) && it.isShown }
                .map {
                    AdItem(
                            id = it.id.toString(),
                            photoUrl = if (it.photos.isNotEmpty()) {
                                it.photos[0].photo
                            } else {
                                EMPTY
                            },
                            date = it.date,
                            title = it.title,
                            price = it.price,
                            place = it.location,
                            views = it.views
                    )
                }

        return ServiceResponse.Success(getPageSublist(list, pageNumber))
    }

    fun getAdRequests(pageNumber: Int): ServiceResponse<List<AdItem>> {
        val list = advertRepository.findAll()
                .sortedByDescending { it.date }
                .filter { !it.isShown }
                .map {
                    AdItem(
                            id = it.id.toString(),
                            photoUrl = if (it.photos.isNotEmpty()) {
                                it.photos[0].photo
                            } else {
                                EMPTY
                            },
                            date = it.date,
                            title = it.title,
                            price = it.price,
                            place = it.location,
                            views = it.views
                    )
                }

        return ServiceResponse.Success(getPageSublist(list, pageNumber))
    }

    fun getSearch(searchText: String, pageNumber: Int, sortType: Int): ServiceResponse<List<AdItem>> {
        if (searchText.isEmpty()) {
            return ServiceResponse.Success(emptyList())
        }

        val list = advertRepository.findAll()
                .sortedByDescending { it.date }
                .filter { it.title.contains(searchText) && it.isShown }
                .map {
                    AdItem(
                            id = it.id.toString(),
                            photoUrl = if (it.photos.isNotEmpty()) {
                                it.photos[0].photo
                            } else {
                                EMPTY
                            },
                            date = it.date,
                            title = it.title,
                            price = it.price,
                            place = it.location,
                            views = it.views
                    )
                }

        return ServiceResponse.Success(getPageSublist(list, pageNumber))
    }

    fun getDetails(detailsId: String): ServiceResponse<AdvertInfo> {
        val details = advertRepository.findByIdOrNull(detailsId.toIntOrNull() ?: -1)
                ?: return ServiceResponse.Success(AdvertInfo(id = detailsId))
        val account = accountRepository.findByIdOrNull(details.userId)
        val response = AdvertInfo(
                id = details.id.toString(),
                photos = details.photos.map { it.photo },
                title = details.title,
                price = details.price,
                location = details.location,
                date = details.date,
                views = details.views,
                synopsis = details.synopsis,
                username = account?.username ?: EMPTY,
                phone = details.phone
        )

        return ServiceResponse.Success(response)
    }

    private fun getPageSublist(list: List<AdItem>, pageNumber: Int): List<AdItem> {
        return when {
            list.size < pageNumber * 30 -> emptyList()
            list.size < pageNumber * 30 + 30 -> list.subList(pageNumber * 30, list.size)
            else -> list.subList(pageNumber * 30, pageNumber * 30 + 30)
        }
    }
}