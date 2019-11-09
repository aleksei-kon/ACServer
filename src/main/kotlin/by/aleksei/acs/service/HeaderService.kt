package by.aleksei.acs.service

import by.aleksei.acs.Constants.Header.BAD_TOKEN
import by.aleksei.acs.Constants.Header.BAD_USER_AGENT
import by.aleksei.acs.Constants.Header.USER_AGENTS
import by.aleksei.acs.repository.AccountRepository
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.HttpHeaders.AUTHORIZATION
import javax.ws.rs.core.HttpHeaders.USER_AGENT

@Component
class HeaderService(private val accountRepository: AccountRepository) {

    fun processHeaders(headers: HttpHeaders, isGuest: Boolean = false): ServiceResponse<String> {
        if (USER_AGENTS.contains(headers.getHeaderString(USER_AGENT))) {
            return ServiceResponse.Error(BAD_USER_AGENT)
        }

        val token = headers.getHeaderString(AUTHORIZATION)

        return if (accountRepository.isTokenExist(token)) {
            ServiceResponse.Success(token)
        } else {
            ServiceResponse.Error(BAD_TOKEN)
        }
    }
}