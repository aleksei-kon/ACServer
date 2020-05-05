package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class AccountConfig(
        val token: String = EMPTY,
        val isloggedin: Boolean = false,
        val isadmin: Boolean = false
)