package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object DonatesPlayerModel : Table("zirconia_donates_player") {
    val id = integer("id").autoIncrement()
    val user = uuid("user")
    val group = varchar("group", length = 256)
    val server = varchar("server", length = 256)
    val expire_in = datetime("expire_in")

    override val primaryKey = PrimaryKey(id)
}