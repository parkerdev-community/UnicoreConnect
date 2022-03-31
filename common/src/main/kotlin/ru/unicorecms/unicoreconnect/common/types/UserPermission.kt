package ru.unicorecms.unicoreconnect.common.types

import java.util.*

class UserPermission {
    lateinit var user: User
    lateinit var permission: DonatePermission
    lateinit var server: Server
    var expired: Date? = null
}