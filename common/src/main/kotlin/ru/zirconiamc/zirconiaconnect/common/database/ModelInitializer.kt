package ru.zirconiamc.zirconiaconnect.common.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.zirconiamc.zirconiaconnect.common.database.model.*

class ModelInitializer {
    fun createSchema() = transaction {
        SchemaUtils.create(
            BanlistModel,
            DonatesPlayerModel,
            PlaytimeModel,
            QueueModel,
            WarehouseModel
        )
    }
}