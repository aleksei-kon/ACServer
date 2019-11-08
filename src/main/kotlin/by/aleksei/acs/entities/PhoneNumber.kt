package by.aleksei.acs.entities

import javax.persistence.*

@Entity
data class PhoneNumber(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(nullable = false)
        val number: String
)
