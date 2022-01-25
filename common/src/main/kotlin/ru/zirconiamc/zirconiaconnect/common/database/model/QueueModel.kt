package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.*

object QueueModel : Table("zirconia_queue") {
    val id = integer("id").autoIncrement()
    val user = varchar("user", 256)
    val server = varchar("server",256)
    val type = varchar("type", 256)
    val context = varchar("context", 256).nullable()

    override val primaryKey = PrimaryKey(id, name = "id")
}