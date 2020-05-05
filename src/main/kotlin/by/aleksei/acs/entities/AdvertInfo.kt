package by.aleksei.acs.entities

data class AdvertInfo(
        val id: Int = -1,
        val photos: List<String>? = null,
        val title: String? = null,
        val price: String? = null,
        val location: String? = null,
        val date: Long? = null,
        val views: Int? = null,
        val synopsis: String? = null,
        val username: String? = null,
        val phone: String? = null,
        val showed: Boolean? = null,
        val bookmark: Boolean? = null
)