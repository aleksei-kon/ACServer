package by.aleksei.acs.service

import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.entities.AdItem
import by.aleksei.acs.entities.AdvertInfo
import by.aleksei.acs.entities.NewDetailsModel
import by.aleksei.acs.entities.OperationStatus
import by.aleksei.acs.entities.db.Advert
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

    fun getBookmarks(pageNumber: Int): ServiceResponse<List<AdItem>> {
        val list = mutableListOf<AdItem>()
        for (i in 1..20) {
            list.add(AdItem(
                    id = (1..8845648).random().toString(),
                    photoUrl = "image4.jpg",
                    title = "Page $pageNumber",
                    price = "1500",
                    place = "Moskou",
                    views = 128
            ))
        }

        return ServiceResponse.Success(list)
    }

    fun getAdRequests(pageNumber: Int): ServiceResponse<List<AdItem>> {
        val list = advertRepository.findAll()
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
        val list = advertRepository.findAll()
                .filter { it.title.contains(searchText) }
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
            list.size < pageNumber * 10 -> emptyList()
            list.size <= pageNumber * 10 + 20 -> list.subList(pageNumber * 10, list.size)
            else -> list.subList(pageNumber * 10, pageNumber * 10 + 20)
        }
    }
}