package ru.unicorecms.unicoreconnect.common.services

import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.Server

class ServerService {
    private val config = UnicoreCommon.config

    fun check(): Server {
        return UnicoreCommon.requester.get("${config.apiUrl}/servers/${config.server}").getOrThrow()
    }
}