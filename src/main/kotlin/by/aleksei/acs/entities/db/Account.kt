package by.aleksei.acs.entities.db

import by.aleksei.acs.Constants.DEFAULT_INT
import by.aleksei.acs.Constants.EMPTY
import javax.persistence.*

@Entity
data class Account(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = DEFAULT_INT,

        @Column(nullable = false)
        val username: String = EMPTY,

        @Column(nullable = false)
        val password: String = EMPTY,

        @Column(nullable = true)
        @OneToMany(cascade = [CascadeType.ALL])
        val tokens: ArrayList<String> = ArrayList()
)