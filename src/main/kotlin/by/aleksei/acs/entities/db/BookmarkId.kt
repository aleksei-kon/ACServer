package by.aleksei.acs.entities.db

import by.aleksei.acs.Constants.DEFAULT_INT
import javax.persistence.*

@Entity
data class BookmarkId(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = DEFAULT_INT,

        @Column(nullable = false)
        val bookmarkId: Int = -1
)