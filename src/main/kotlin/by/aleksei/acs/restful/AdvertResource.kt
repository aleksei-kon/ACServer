package by.aleksei.acs.restful

import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.Constants.EMPTY_ID
import by.aleksei.acs.Constants.FIRST_PAGE
import by.aleksei.acs.Constants.SortTypes.ALPHABET
import by.aleksei.acs.entities.NewDetailsModel
import by.aleksei.acs.entities.UpdateDetailsModel
import by.aleksei.acs.service.AdvertService
import by.aleksei.acs.service.HeaderService
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("advert/")
class AdvertResource(
        private val headerService: HeaderService,
        private val advertService: AdvertService) : BaseResource() {

    @POST
    @Path("newDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addAdvert(@Context headers: HttpHeaders, advertInfo: NewDetailsModel): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.add(headerResult.data, advertInfo))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("addRemoveToBookmark")
    fun addRemoveToBookmark(@Context headers: HttpHeaders, @QueryParam("id") adId: Int = EMPTY_ID): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> {
                    when (advertService.addRemoveToBookmark(headerResult.data, adId)) {
                        true -> Response.ok().status(Response.Status.ACCEPTED).build()
                        else -> Response.ok().status(Response.Status.BAD_REQUEST).build()
                    }
                }
                is ServiceResponse.Error -> Response.ok().status(Response.Status.BAD_REQUEST).build()
            }

    @GET
    @Path("showHideAd")
    fun showHideAd(@Context headers: HttpHeaders, @QueryParam("id") adId: Int = EMPTY_ID): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> {
                    when (advertService.showHideAd(headerResult.data, adId)) {
                        true -> Response.ok().status(Response.Status.ACCEPTED).build()
                        else -> Response.ok().status(Response.Status.BAD_REQUEST).build()
                    }
                }
                is ServiceResponse.Error -> Response.ok().status(Response.Status.BAD_REQUEST).build()
            }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateAdvert(@Context headers: HttpHeaders, advertInfo: UpdateDetailsModel): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> {
                    when (advertService.update(headerResult.data, advertInfo)) {
                        true -> Response.ok().status(Response.Status.ACCEPTED).build()
                        else -> Response.ok().status(Response.Status.BAD_REQUEST).build()
                    }
                }
                is ServiceResponse.Error -> Response.ok().status(Response.Status.BAD_REQUEST).build()
            }

    @GET
    @Path("delete")
    fun deleteAdvert(@Context headers: HttpHeaders, @QueryParam("advertId") advertId: Int = EMPTY_ID): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> {
                    when (advertService.delete(headerResult.data, advertId)) {
                        true -> Response.ok().status(Response.Status.ACCEPTED).build()
                        else -> Response.ok().status(Response.Status.BAD_REQUEST).build()
                    }
                }
                is ServiceResponse.Error -> Response.ok().status(Response.Status.BAD_REQUEST).build()
            }

    @GET
    @Path("lastAds")
    @Produces(MediaType.APPLICATION_JSON)
    fun getLastAds(@Context headers: HttpHeaders,
                   @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE): Response =
            when (val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getLastAds(pageNumber))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("myAds")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMyAds(@Context headers: HttpHeaders,
                 @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getMyAds(headerResult.data, pageNumber))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("bookmarks")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBookmarks(@Context headers: HttpHeaders,
                     @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getBookmarks(headerResult.data, pageNumber))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    fun getSearch(@Context headers: HttpHeaders,
                  @QueryParam("searchText") searchText: String = EMPTY,
                  @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE,
                  @QueryParam("searchType") searchType: Int = ALPHABET): Response =
            when (
                val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getSearch(searchText, pageNumber, searchType))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("adRequests")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAdRequests(@Context headers: HttpHeaders,
                      @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getAdRequests(pageNumber))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("details")
    @Produces(MediaType.APPLICATION_JSON)
    fun getDetails(@Context headers: HttpHeaders,
                   @QueryParam("detailsId") detailsId: Int = EMPTY_ID): Response =
            when (val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getJsonResponse(advertService.getDetails(headerResult.data, detailsId))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }
}
