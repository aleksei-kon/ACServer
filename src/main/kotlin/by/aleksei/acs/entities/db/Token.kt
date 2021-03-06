package by.aleksei.acs.entities.db

import by.aleksei.acs.Constants.DEFAULT_INT
import by.aleksei.acs.Constants.EMPTY
import javax.persistence.*

@Entity
data class Token(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = DEFAULT_INT,

        @Column(nullable = false)
        val token: String = EMPTY
)