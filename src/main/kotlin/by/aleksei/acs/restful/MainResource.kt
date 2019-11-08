package by.aleksei.acs.restful

import by.aleksei.acs.entities.UserInfo
import by.aleksei.acs.repository.UserRepository
import by.aleksei.acs.service.HeaderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.HttpHeaders.USER_AGENT
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("/")
class MainResource @Autowired constructor(val headerService: HeaderService,
                                          val repo: UserRepository) {

    data class Message(val text: String)

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(@Context headers: HttpHeaders): Response {
        val info = headers.getRequestHeader(USER_AGENT)[0]

        return Response.ok(Message("Hello from kotlin spring boot $info")).build()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun auth(): Response {
        return Response.ok().build()
    }

    @GET
    @Path("testdb")
    @Produces(MediaType.APPLICATION_JSON)
    fun testdb(): Response {
        val user = UserInfo(
                name = "Aleksei",
                email = "aleksei@gmail.com"
        )

        repo.save(user)

        return Response.ok("work").build()
    }
}