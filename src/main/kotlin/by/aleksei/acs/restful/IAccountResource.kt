package by.aleksei.acs.restful

import by.aleksei.acs.entities.AccountInfo
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response

interface IAccountResource {

    fun register(headers: HttpHeaders, accountInfo: AccountInfo): Response

    fun login(headers: HttpHeaders, accountInfo: AccountInfo): Response

    fun delete(headers: HttpHeaders): Response
}