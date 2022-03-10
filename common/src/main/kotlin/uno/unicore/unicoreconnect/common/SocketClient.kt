package uno.unicore.unicoreconnect.common

import io.socket.client.IO
import io.socket.client.Socket
import org.greenrobot.eventbus.EventBus
import uno.unicore.unicoreconnect.common.events.SocketEvent
import uno.unicore.unicoreconnect.common.types.User
import uno.unicore.unicoreconnect.common.types.UserDonate
import java.util.Collections.singletonList
import java.util.Collections.singletonMap
import java.util.logging.Logger

class SocketClient(private  val logger: Logger) {
    private var config = UnicoreCommon.config
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
                val data = UnicoreCommon.gson.fromJson(args[0].toString(), User::class.java)

                if (!data.perms.contains("kernel.unicore.connect")) {
                    logger.warning("'apiKey' not have permission 'kernel.unicore.connect'")
                } else {
                    logger.info("Successfully connected to UnicoreCMS socket")
                }
            }
        }

        socket?.on("buy_donate") { args ->
            val payload = UnicoreCommon.gson.fromJson(args[0].toString(), UserDonate::class.java)

            if (payload.server.id == config.server) {
                EventBus.getDefault().post(SocketEvent.BUY_DONATE(payload))
                UnicoreCommon.donateGroupService.add(payload)
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