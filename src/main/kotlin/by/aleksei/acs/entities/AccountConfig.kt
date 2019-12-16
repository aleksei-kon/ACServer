package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class AccountConfig(
        val token: String = EMPTY,
        val isloggedin: String = "false",
        val isadmin: String = "false"
)