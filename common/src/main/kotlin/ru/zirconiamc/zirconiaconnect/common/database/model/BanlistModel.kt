package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table

object BanlistModel : Table("zirconia_banlist") {
    val user = varchar("user", 255).uniqueIndex()
    val actor = varchar("actor", length = 256)
    val reason = varchar("reason", length = 256)
    val created = long("created")
    val expires = long("expires")

    override val primaryKey = PrimaryKey(user)
}