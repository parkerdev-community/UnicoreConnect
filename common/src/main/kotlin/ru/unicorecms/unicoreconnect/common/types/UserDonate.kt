package ru.unicorecms.unicoreconnect.common.types

import java.util.Date

class UserDonate {
    lateinit var user: User
    lateinit var group: DonateGroup
    lateinit var server: Server
    var expired: Date? = null
}