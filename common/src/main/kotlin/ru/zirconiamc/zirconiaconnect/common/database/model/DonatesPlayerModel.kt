package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table

object DonatesPlayerModel : Table("zirconia_donates_player") {
    val id = integer("id").autoIncrement()
    val user = varchar("user", length = 256)
    val group = varchar("group", length = 256)
    val server = varchar("server", length = 256)
    val expire_in = integer("expire_in")

    override val primaryKey = PrimaryKey(id)
}