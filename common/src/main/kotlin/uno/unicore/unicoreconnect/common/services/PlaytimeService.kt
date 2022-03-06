package uno.unicore.unicoreconnect.common.services

import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.types.Playtime
import uno.unicore.unicoreconnect.common.types.PlaytimeRequest
import java.util.UUID

class PlaytimeService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/cabinet/playtime"

    fun findOne(uuid: UUID): Playtime? {
        return try {
            UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow()
        } catch (_: Exception) {
            null
        }
    }

    fun findTop(): List<Playtime>? {
        return try {
            UnicoreCommon.requester.get("$baseUrl/${config.server}").getOrThrow()
        } catch (_: Exception) {
            null
        }
    }

    fun update(uuids: List<UUID>) {
        UnicoreCommon.requester.patch(baseUrl, uuids.map { PlaytimeRequest(it.toString(), config.server) })
    }
}