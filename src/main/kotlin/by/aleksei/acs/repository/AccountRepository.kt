package by.aleksei.acs.repository

import by.aleksei.acs.entities.db.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Int> {

    fun getAccountByToken(token: String): Account? =
            findAll().find { it.tokens.contains(token) }

    fun getAccountByName(username: String): Account? =
            findAll().find { it.username == username }

    fun getAccountByNameAndPassword(username: String, password: String): Account? =
            findAll().find { it.username == username && it.password == password }

    fun isUsernameExist(username: String): Boolean = findAll()
            .map { it.username }
            .any { it == username }

    fun isTokenExist(token: String): Boolean = findAll()
            .map { it.tokens }
            .flatten()
            .any { it == token }
}
