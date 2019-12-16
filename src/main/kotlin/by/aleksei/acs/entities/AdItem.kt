package by.aleksei.acs.entities

import by.aleksei.acs.Constants.EMPTY

data class AdItem(
        val id: String = EMPTY,
        val photoUrl: String? = null,
        val title: String? = null,
        val price: String? = null,
        val place: String? = null,
        val views: Int? = null,
        val date: Long? = null
)