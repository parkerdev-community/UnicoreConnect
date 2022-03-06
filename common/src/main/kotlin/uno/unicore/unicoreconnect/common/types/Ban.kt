package uno.unicore.unicoreconnect.common.types

import java.util.Date

class Ban {
    lateinit var user: User
    lateinit var actor: User
    lateinit var reason: String

    var expires: Date? = null
    lateinit var created: Date
}