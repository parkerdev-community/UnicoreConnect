package uno.unicore.unicoreconnect.common.services

import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.types.Server

class ServerService {
    private val config = UnicoreCommon.config

    fun check(): Server? {
        return try {
            UnicoreCommon.requester.get("${config.apiUrl}/servers/${config.server}").getOrThrow()
        } catch (_: Exception) {
            null
        }
    }
}