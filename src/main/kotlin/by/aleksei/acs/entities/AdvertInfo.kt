package by.aleksei.acs.entities

import by.aleksei.acs.Constants.DEFAULT_DOUBLE
import by.aleksei.acs.Constants.EMPTY

data class AdvertInfo(
        val title: String = EMPTY,
        val price: Double = DEFAULT_DOUBLE,
        val description: String = EMPTY,
        val phone: String = EMPTY,
        val photos: MutableList<String> = mutableListOf()
)