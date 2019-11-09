package by.aleksei.acs.service

import by.aleksei.acs.Constants.Account.USERNAME_EXIST_MESSAGE
import by.aleksei.acs.Constants.Account.USERNAME_NOT_EXIST_MESSAGE
import by.aleksei.acs.Constants.Account.USERNAME_OR_PASSWORD_NOT_MATCH_MESSAGE
import by.aleksei.acs.entities.AccountInfo
import by.aleksei.acs.entities.OperationStatus
import by.aleksei.acs.entities.TokenInfo
import by.aleksei.acs.entities.db.Account
import by.aleksei.acs.entities.db.Token
import by.aleksei.acs.repository.*
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.*

@Component
class AccountService(private val accountRepository: AccountRepository) {

    fun register(accountInfo: AccountInfo): ServiceResponse<TokenInfo> {
        if (accountRepository.isUsernameExist(accountInfo.username)) {
            return ServiceResponse.Error(USERNAME_EXIST_MESSAGE)
        }

        val token = generateUniqueToken()

        accountRepository.save(
                Account(
                        username = accountInfo.username,
                        password = accountInfo.password,
                        tokens = mutableListOf(Token(token = token))
                )
        )

        return ServiceResponse.Success(TokenInfo(token))
    }

    fun login(accountInfo: AccountInfo): ServiceResponse<TokenInfo> {
        if (!accountRepository.isUsernameExist(accountInfo.username)) {
            return ServiceResponse.Error(USERNAME_NOT_EXIST_MESSAGE)
        }

        val account = accountRepository.getAccountByNameAndPassword(
                accountInfo.username,
                accountInfo.password
        )

        return if (account != null) {
            val token = generateUniqueToken()
            account.tokens.add(Token(token = token))
            accountRepository.save(account)

            ServiceResponse.Success(TokenInfo(token))
        } else {
            ServiceResponse.Error(USERNAME_OR_PASSWORD_NOT_MATCH_MESSAGE)
        }
    }

    fun delete(token: String): ServiceResponse<OperationStatus> {
        val account = accountRepository.getAccountByToken(token)

        return if (account != null) {
            accountRepository.delete(account)

            ServiceResponse.Success(OperationStatus(true))
        } else {
            ServiceResponse.Success(OperationStatus(false))
        }
    }

    private fun generateUniqueToken(): String {
        val newToken = generateToken()

        return if (accountRepository.isTokenExist(newToken)) {
            generateUniqueToken()
        } else {
            newToken
        }
    }

    private fun generateToken(): String {
        val bytes = ByteArray(32)
        SecureRandom().nextBytes(bytes)

        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }
}