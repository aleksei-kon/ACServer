package by.aleksei.acs.restful

import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.Constants.EMPTY_ID
import by.aleksei.acs.Constants.FIRST_PAGE
import by.aleksei.acs.Constants.SortTypes.DATE_DESC
import by.aleksei.acs.entities.AdvertInfo
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
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addAdvert(@Context headers: HttpHeaders, advertInfo: AdvertInfo): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getResponse(advertService.add(headerResult.data, advertInfo))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateAdvert(@Context headers: HttpHeaders, advertInfo: AdvertInfo): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getResponse(advertService.update(headerResult.data, advertInfo))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteAdvert(@Context headers: HttpHeaders,
               @QueryParam("advertId") advertId: Int = EMPTY_ID): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getResponse(advertService.delete(headerResult.data, advertId))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAdvert(@Context headers: HttpHeaders,
            @QueryParam("pageNumber") pageNumber: Int = FIRST_PAGE,
            @QueryParam("username") username: String = EMPTY,
            @QueryParam("sortType") sortType: Int = DATE_DESC): Response =
            when (val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getResponse(advertService.get(pageNumber, username, sortType))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }
}
