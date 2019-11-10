package by.aleksei.acs.service

import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.Constants.Header.BAD_TOKEN
import by.aleksei.acs.repository.AccountRepository
import by.aleksei.acs.repository.isTokenExist
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.HttpHeaders.AUTHORIZATION

@Component
class HeaderService(private val accountRepository: AccountRepository) {

    fun processHeaders(headers: HttpHeaders, isGuest: Boolean = false): ServiceResponse<String> {
        val token = headers.getHeaderString(AUTHORIZATION)

        return when {
            isGuest -> ServiceResponse.Success(EMPTY)
            token == null -> ServiceResponse.Error(BAD_TOKEN)
            accountRepository.isTokenExist(token) -> ServiceResponse.Success(token)
            else -> ServiceResponse.Error(BAD_TOKEN)
        }
    }
}