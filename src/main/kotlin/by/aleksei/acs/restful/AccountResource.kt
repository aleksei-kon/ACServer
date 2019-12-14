package by.aleksei.acs.restful

import by.aleksei.acs.entities.AccountInfo
import by.aleksei.acs.service.AccountService
import by.aleksei.acs.service.HeaderService
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("account/")
class AccountResource(
        private val headerService: HeaderService,
        private val accountService: AccountService) : BaseResource() {

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun registerAccount(@Context headers: HttpHeaders, accountInfo: AccountInfo): Response =
            when (val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getResponse(accountService.register(accountInfo))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun loginAccount(@Context headers: HttpHeaders, accountInfo: AccountInfo): Response =
            when (val headerResult = headerService.processHeaders(headers, true)) {
                is ServiceResponse.Success -> getResponse(accountService.login(accountInfo))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }

    @GET
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteAccount(@Context headers: HttpHeaders): Response =
            when (val headerResult = headerService.processHeaders(headers)) {
                is ServiceResponse.Success -> getResponse(accountService.delete(headerResult.data))
                is ServiceResponse.Error -> Response.ok(headerResult).build()
            }
}