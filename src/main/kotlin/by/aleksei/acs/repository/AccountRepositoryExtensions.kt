package by.aleksei.acs.repository

import by.aleksei.acs.entities.db.Account

fun AccountRepository.isUsernameExist(username: String): Boolean = findAll()
        .map { it.username }
        .any { it == username }

fun AccountRepository.isTokenExist(token: String): Boolean = findAll()
        .map { it.tokens }
        .flatten()
        .any { it.token == token }

fun AccountRepository.getAccountByName(username: String): Account? =
        findAll().find { it.username == username }

fun AccountRepository.getAccountByNameAndPassword(username: String, password: String): Account? =
        findAll().find { it.username == username && it.password == password }

fun AccountRepository.getAccountByToken(token: String): Account? =
        findAll().find { account -> account.tokens.any { t -> t.token == token } }