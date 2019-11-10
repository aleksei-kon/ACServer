package by.aleksei.acs.entities.db

import by.aleksei.acs.Constants.DEFAULT_DOUBLE
import by.aleksei.acs.Constants.DEFAULT_INT
import by.aleksei.acs.Constants.EMPTY
import by.aleksei.acs.Constants.UNIXTIME_DIVIDER
import java.util.*
import javax.persistence.*

data class Advert(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = DEFAULT_INT,

        @Column(nullable = false)
        val title: String = EMPTY,

        @Column(nullable = false)
        val price: Double = DEFAULT_DOUBLE,

        @Column(nullable = false)
        val description: String = EMPTY,

        @Column(nullable = false)
        val phone: String = EMPTY,

        @Column(nullable = false)
        val unixtimeDate: Long = Date().time / UNIXTIME_DIVIDER,

        @Column(nullable = false)
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val photos: MutableList<String> = mutableListOf()
)