package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table

object PlaytimeModel : Table("zirconia_playtime") {
    val uuid = varchar("uuid", length = 256)
    val server = varchar("server", length = 256)
    val playtime = integer("playtime")
}