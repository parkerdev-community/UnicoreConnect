package ru.unicorecms.unicoreconnect.common.types

class User {
    lateinit var uuid: String
    lateinit var username: String
    lateinit var perms: List<String>
    var ban: Ban? = null
}