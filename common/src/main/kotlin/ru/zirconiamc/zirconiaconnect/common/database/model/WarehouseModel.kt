package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table

object WarehouseModel : Table("zirconia_warehouse") {
    val id = integer("id").autoIncrement()
    val user = uuid("uuid")
    val server = varchar("server", length = 256)
    val item = varchar("item", length = 256)
    val amount = integer("amount")

    override val primaryKey = PrimaryKey(id)
}