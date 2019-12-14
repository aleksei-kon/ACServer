package by.aleksei.acs

object Constants {

    const val EMPTY = ""
    const val DEFAULT_INT = 0
    const val DEFAULT_DOUBLE = 0.0
    const val UNIXTIME_DIVIDER = 1000L
    const val EMPTY_ID = -1
    const val FIRST_PAGE = 0

    object Header {

        const val BAD_TOKEN = "Bat token"
    }

    object Account {

        const val USERNAME_EXIST_MESSAGE = "Such username already exists"
        const val USERNAME_NOT_EXIST_MESSAGE = "Such username not exists"
        const val USERNAME_OR_PASSWORD_NOT_MATCH_MESSAGE = "Username or password do not match"
    }

    object SortTypes {

        const val ALPHABET = 1
        const val DATE_ASC = 2
        const val DATE_DESC = 3
        const val PRICE_ASC = 4
        const val PRICE_DESC = 5
    }

    object Image {

        const val DEFAULT_PATH = "D:/images/"
        const val ATTACHMENT_FILE = "attachment; filename"
    }
}