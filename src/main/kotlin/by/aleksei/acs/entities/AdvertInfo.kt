package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class AdvertInfo(
        val id: String = EMPTY,
        val isShown: String? = null,
        val isBookmark: String? = null,
        val photos: List<String>? = null,
        val title: String? = null,
        val price: String? = null,
        val location: String? = null,
        val date: Long? = null,
        val views: Int? = null,
        val synopsis: String? = null,
        val username: String? = null,
        val phone: String? = null
)