package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class UpdateDetailsModel(
        val id: Int = -1,
        val photos: List<String> = emptyList(),
        val title: String = EMPTY,
        val price: String = EMPTY,
        val location: String = EMPTY,
        val synopsis: String = EMPTY,
        val phone: String = EMPTY
)