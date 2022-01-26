package ru.zirconiamc.zirconiaconnect.common.database.model

import org.jetbrains.exposed.sql.Table
import ru.zirconiamc.zirconiaconnect.common.database.model.BanlistModel.autoIncrement

object PlaytimeModel : Table("zirconia_playtime") {
    val id = integer("id").autoIncrement()
    val uuid = uuid("uuid")
    val server = varchar("server", length = 256)
    val playtime = integer("playtime")

    override val primaryKey = PrimaryKey(id)
}