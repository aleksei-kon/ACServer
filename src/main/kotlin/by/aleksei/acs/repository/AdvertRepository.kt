package by.aleksei.acs.repository

import by.aleksei.acs.entities.db.Advert
import org.springframework.data.repository.CrudRepository

interface AdvertRepository : CrudRepository<Advert, Int>