package ru.unicorecms.unicoreconnect.common.types

import java.util.Date

class Ban {
    lateinit var user: User
    var actor: User? = null
    lateinit var reason: String

    var expires: Date? = null
    lateinit var created: Date
}