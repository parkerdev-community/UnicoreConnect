package ru.unicorecms.unicoreconnect.common.services

import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.Playtime
import ru.unicorecms.unicoreconnect.common.types.PlaytimeRequest
import java.util.UUID

class PlaytimeService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/cabinet/playtime"

    fun findOne(uuid: UUID): Playtime {
        return UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow()
    }

    fun findTop(): Array<Playtime> {
        return UnicoreCommon.requester.get("$baseUrl/${config.server}").getOrThrow()
    }

    fun update(uuids: List<UUID>) {
        UnicoreCommon.requester.patch(baseUrl, uuids.map { PlaytimeRequest(it.toString(), config.server) })
    }
}