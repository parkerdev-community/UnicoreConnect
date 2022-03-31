package ru.unicorecms.unicoreconnect.common.services

import me.confuser.banmanager.common.api.BmAPI
import me.confuser.banmanager.common.data.PlayerBanData
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.Ban
import ru.unicorecms.unicoreconnect.common.types.BanRequest
import java.util.UUID

class BanService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/bans"

    fun handleJoin(uuid: UUID) {
        val isBanned = UnicoreCommon.requester.get("$baseUrl/$uuid")
        val bmIsBanned = BmAPI.isBanned(uuid)

        if (!isBanned.response.isSuccessful && bmIsBanned) {
            val banData = BmAPI.getCurrentBan(uuid)
            BmAPI.unban(banData, BmAPI.getConsole())
        } else if (isBanned.response.isSuccessful && !bmIsBanned) {
            val banData = isBanned.getOrThrow<Ban>()
            val actor = if (banData.actor == null) BmAPI.getConsole() else BmAPI.getPlayer(UUID.fromString(banData.actor!!.uuid))
            val player = BmAPI.getPlayer(uuid)

            if (banData.expires != null) {
                println(banData.expires!!.time / 1000)
                BmAPI.ban(player, actor, banData.reason, true, banData.expires!!.time / 1000)
            } else {
                BmAPI.ban(player, actor, banData.reason, true)
            }
        }
    }

    fun create(ban: PlayerBanData) {
        val actor: String? = if (BmAPI.getConsole().id.contentEquals(ban.actor.id)) {
            null
        } else {
            ban.actor.uuid.toString()
        }

        UnicoreCommon.requester.post(
            baseUrl, BanRequest(
                ban.player.uuid.toString(),
                ban.reason,
                actor,
                ban.expires
            )
        )
    }

    fun delete(ban: PlayerBanData) {
        UnicoreCommon.requester.delete("$baseUrl/${ban.player.uuid}")
    }
}
