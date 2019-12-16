package by.aleksei.acs.entities.db

import by.aleksei.acs.Constants.DEFAULT_INT
import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.Constants.UNIXTIME_DIVIDER
import java.util.*
import javax.persistence.*

@Entity
data class Advert(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = DEFAULT_INT,

        @Column(nullable = false)
        val title: String = EMPTY,

        @Column(nullable = false)
        val price: String = EMPTY,

        @Column(nullable = false)
        val userId: Int = -1,

        @Column(nullable = false)
        val isShown: Boolean = false,

        @Column(nullable = false)
        val location: String = EMPTY,

        @Column(nullable = false)
        val views: Int = 0,

        @Column(nullable = false)
        val synopsis: String = EMPTY,

        @Column(nullable = false)
        val phone: String = EMPTY,

        @Column(nullable = false)
        val date: Long = Date().time / UNIXTIME_DIVIDER,

        @Column(nullable = false)
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val photos: MutableList<Photo> = mutableListOf()
)

