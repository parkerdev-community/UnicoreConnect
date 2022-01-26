package ru.zirconiamc.zirconiaconnect.common.services

import me.confuser.banmanager.common.data.PlayerBanData
import org.jetbrains.exposed.sql.transactions.transaction
import ru.zirconiamc.zirconiaconnect.common.database.model.BanlistModel
import me.confuser.banmanager.common.api.BmAPI
import net.dzikoysk.exposed.upsert.upsert
import org.jetbrains.exposed.sql.*
import ru.zirconiamc.zirconiaconnect.common.ZirconiaInstance


class BanService {
    var server = ZirconiaInstance.server

    fun ban(ban: PlayerBanData) = transaction {
        BanlistModel.upsert(BanlistModel.user,
            insertBody = {
                it[user] = ban.player.uuid.toString()
                it[reason] = ban.reason
                it[created] = ban.created
                it[expires] = ban.expires

                if (!ban.actor.equals(BmAPI.getConsole()))
                    it[actor] = ban.actor.uuid.toString()
                else
                    it[actor] = ""
            },
            updateBody = {
                it[reason] = ban.reason
                it[expires] = ban.expires

                if (!ban.actor.equals(BmAPI.getConsole()))
                    it[actor] = ban.actor.uuid.toString()
                else
                    it[actor] = ""
            }
        )
    }

    fun unban(ban: PlayerBanData) = transaction {
        BanlistModel.deleteWhere { BanlistModel.user eq ban.player.uuid.toString() }
    }
}
