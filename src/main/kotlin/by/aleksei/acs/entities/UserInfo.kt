package by.aleksei.acs.entities

import javax.persistence.*

@Entity
data class UserInfo(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @Column(nullable = false)
        val name: String = "",

        @Column(nullable = true)
        val email: String? = null,

        @Column(nullable = true)
        @OneToMany(cascade = [CascadeType.ALL])
        val phoneNumbers: List<PhoneNumber>? = null
)