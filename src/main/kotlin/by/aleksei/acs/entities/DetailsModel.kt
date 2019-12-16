package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class DetailsModel(
        val id: String = EMPTY,
        val photos: List<String> = emptyList(),
        val isBookmark: String = "false",
        val isShown: String = "false",
        val title: String = EMPTY,
        val price: String = EMPTY,
        val location: String = EMPTY,
        val date: String = EMPTY,
        val views: String = EMPTY,
        val synopsis: String = EMPTY,
        val username: String = EMPTY,
        val phone: String = EMPTY
)