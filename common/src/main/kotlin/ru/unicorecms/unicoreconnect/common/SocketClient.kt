package ru.unicorecms.unicoreconnect.common

import io.socket.client.IO
import io.socket.client.Socket
import org.greenrobot.eventbus.EventBus
import ru.unicorecms.unicoreconnect.common.events.SocketEvent
import ru.unicorecms.unicoreconnect.common.types.User
import ru.unicorecms.unicoreconnect.common.types.UserDonate
import java.util.Collections.singletonList
import java.util.Collections.singletonMap
import java.util.logging.Logger

class SocketClient(private  val logger: Logger) {
    private var config = ru.unicorecms.unicoreconnect.common.UnicoreCommon.Companion.config
    private var socket: Socket? = null

    fun reconnectHandler() {
        if (socket?.connected() == false) {
            socket?.connect()
        }
    }

    fun connect() {
        socket = IO.socket(config.apiUrl, IO.Options().apply {
            extraHeaders = singletonMap("authorization", singletonList("Api-Key ${config.apiKey}"))
            timestampRequests = true
            secure = true
            forceNew = true
            reconnection = false
        })

        socket?.on("me") { args ->
            if (args[0] === null) {
                logger.warning("'apiKey' incorrect")
            } else {
                val data = ru.unicorecms.unicoreconnect.common.UnicoreCommon.Companion.gson.fromJson(args[0].toString(), User::class.java)

                if (!data.perms.contains("kernel.unicore.connect")) {
                    logger.warning("'apiKey' not have permission 'kernel.unicore.connect'")
                } else {
                    logger.info("Successfully connected to UnicoreCMS socket")
                }
            }
        }

        socket?.on("buy_donate") { args ->
            val payload = ru.unicorecms.unicoreconnect.common.UnicoreCommon.Companion.gson.fromJson(args[0].toString(), UserDonate::class.java)

            if (payload.server.id == config.server) {
                EventBus.getDefault().post(SocketEvent.BUY_DONATE(payload))
                ru.unicorecms.unicoreconnect.common.UnicoreCommon.Companion.donateGroupService.add(payload)
            }
        }

        socket?.on(Socket.EVENT_DISCONNECT) {
            logger.info("Reconnecting to UnicoreCMS socket...")
        }

        socket?.connect()
    }

    fun close() {
        socket?.off()
    }
}