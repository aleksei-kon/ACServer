package by.aleksei.acs

object Constants {

    const val DEFAULT_INT = 0
    const val EMPTY = ""

    object Header {

        private const val ANDROID_CLIENT = "a_c_android_client"

        val USER_AGENTS = mutableListOf(ANDROID_CLIENT)

        const val BAD_USER_AGENT = "Bat user agent"
        const val BAD_TOKEN = "Bat token"
    }

    object Account {

        const val USERNAME_EXIST_MESSAGE = "Such username already exists"
        const val USERNAME_NOT_EXIST_MESSAGE = "Such username not exists"
        const val USERNAME_OR_PASSWORD_NOT_MATCH_MESSAGE = "Username or password do not match"
    }
}