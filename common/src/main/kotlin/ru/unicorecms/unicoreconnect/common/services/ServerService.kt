package ru.unicorecms.unicoreconnect.common.services

import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.Server
import java.util.logging.Logger

class ServerService {
    private val config = UnicoreCommon.config

    fun check(logger: Logger): Server? {
        return try {
            UnicoreCommon.requester.get("${config.apiUrl}/servers/${config.server}").getOrThrow()
        } catch (e: Exception) {
            logger.warning(e.message)
            null
        }
    }
}