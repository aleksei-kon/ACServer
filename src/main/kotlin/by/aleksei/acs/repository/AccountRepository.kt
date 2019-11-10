package by.aleksei.acs.repository

import by.aleksei.acs.entities.db.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Int>
