package by.aleksei.acs.service

import by.aleksei.acs.entities.AdItem
import by.aleksei.acs.entities.AdvertInfo
import by.aleksei.acs.entities.OperationStatus
import by.aleksei.acs.entities.db.Advert
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component

@Component
class AdvertService {

    fun add(token: String, advertInfo: AdvertInfo): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
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
        val list = mutableListOf<AdItem>()

        for (i in 1..20) {
            list.add(AdItem(
                    id = (1..9845612).random().toString(),
                    photoUrl = "image2.jpg",
                    title = "Page $pageNumber",
                    price = "100",
                    place = "Grodno",
                    views = 15
            ))
        }

        return ServiceResponse.Success(list)
    }

    fun getMyAds(pageNumber: Int): ServiceResponse<List<AdItem>> {
        val list = mutableListOf<AdItem>()

        for (i in 1..20) {
            list.add(AdItem(
                    id = (1..9845648).random().toString(),
                    photoUrl = "image3.jpg",
                    title = "Page $pageNumber",
                    price = "200",
                    place = "Minsk",
                    views = 2003
            ))
        }

        return ServiceResponse.Success(list)
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
        val list = mutableListOf<AdItem>()

        for (i in 1..40) {
            list.add(AdItem(
                    id = (1..8845648).random().toString(),
                    photoUrl = "image4.jpg",
                    title = "Page $pageNumber",
                    date = 8946546546
            ))
        }

        return ServiceResponse.Success(list)
    }

    fun getSearch(searchText: String, pageNumber: Int, sortType: Int): ServiceResponse<List<AdItem>> {
        val list = mutableListOf<AdItem>()

        for (i in 1..20) {
            list.add(AdItem(
                    id = (1..9846648).random().toString(),
                    photoUrl = "image5.jpg",
                    title = "Page $pageNumber $searchText",
                    price = "180",
                    place = "Gomel",
                    views = 500
            ))
        }

        return ServiceResponse.Success(list)
    }

    fun getDetails(detailsId: String): ServiceResponse<AdvertInfo> {
        val response = AdvertInfo(
                id = detailsId,
                photos = listOf(
                        "image2.jpg",
                        "image3.jpg",
                        "image4.jpg",
                        "test",
                        "image5.jpg"
                ),
                isBookmark = true,
                title = "Title $detailsId",
                price = "100 byn",
                location = "Grodno",
                date = 1576428047,
                views = 1600,
                synopsis = "It is a long established fact that a reader will be distracted by the readable content of a" +
                        " page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal" +
                        " distribution of letters, as opposed to using 'Content here, content here', making it look like readable" +
                        " English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default" +
                        " model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. ",
                username = "Aleksei",
                phone = "375292462199"
        )

        return ServiceResponse.Success(response)
    }
}