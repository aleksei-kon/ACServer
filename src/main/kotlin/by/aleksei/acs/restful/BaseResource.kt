package by.aleksei.acs.restful

import by.aleksei.acs.util.ServiceResponse
import javax.ws.rs.core.Response

abstract class BaseResource {

    protected fun <T> getJsonResponse(result: ServiceResponse<T>): Response =
            when (result) {
                is ServiceResponse.Success -> Response.ok(result.data).build()
                is ServiceResponse.Error -> Response.ok(result).build()
            }
}