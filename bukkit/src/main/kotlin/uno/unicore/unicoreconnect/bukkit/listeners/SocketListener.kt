package uno.unicore.unicoreconnect.bukkit.listeners

import org.bukkit.Bukkit
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.events.SocketEvent

class SocketListener {
    fun register() {
        EventBus.getDefault().register(this)
    }

    fun unregister() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSocketConnect(event: SocketEvent.CONNECT) {
        Bukkit.getLogger().info(event.message)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSocketReconnect(event: SocketEvent.RECONNECT) {
        Bukkit.getLogger().info(event.message)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSocketClose(event: SocketEvent.CLOSE) {
        Bukkit.getLogger().info(event.message)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSocketError(event: SocketEvent.ERROR) {
        UnicoreCommon.socketClient.close()
        Bukkit.getLogger().warning(event.message)
    }
}