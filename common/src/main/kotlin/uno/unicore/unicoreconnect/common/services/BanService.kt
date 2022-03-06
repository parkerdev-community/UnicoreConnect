package uno.unicore.unicoreconnect.common.services

import me.confuser.banmanager.common.api.BmAPI
import me.confuser.banmanager.common.data.PlayerBanData
import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.http.UnicoreRequester
import uno.unicore.unicoreconnect.common.types.BanRequest
import java.io.IOException
import java.util.UUID

class BanService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/bans"

    fun handleJoin(uuid: UUID) {
        val isBanned = UnicoreCommon.requester.get("$baseUrl/$uuid").response.isSuccessful

        if (!isBanned && BmAPI.isBanned(uuid)) {
            val banData = BmAPI.getCurrentBan(uuid)
            BmAPI.unban(banData, BmAPI.getConsole())
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
