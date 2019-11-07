package by.aleksei.acs.restful

import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("hello")
class MainResource {

    data class Message(val text: String)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(): Response {

        return Response.ok(Message("Hello from kotlin spring boot")).build()
    }
}