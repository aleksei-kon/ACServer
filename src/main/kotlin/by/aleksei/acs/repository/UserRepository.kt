package by.aleksei.acs.repository

import by.aleksei.acs.entities.UserInfo
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserInfo, Int>