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

        @Column(nullable = false)
        val isAdmin: Boolean = false,

        @Column(nullable = false)
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val tokens: MutableList<Token> = mutableListOf(),

        @Column(nullable = false)
        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        @JoinTable
        val bookmarkIds: MutableList<BookmarkId> = mutableListOf()
)