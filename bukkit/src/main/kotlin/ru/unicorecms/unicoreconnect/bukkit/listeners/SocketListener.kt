package ru.unicorecms.unicoreconnect.bukkit.listeners

import org.bukkit.Bukkit
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.common.events.SocketEvent

class SocketListener {
    private val plugin = PluginInstance.plugin

    fun register() {
        EventBus.getDefault().register(this)
    }

    fun unregister() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBuyDonate(event: SocketEvent.BUY_DONATE) {
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(ru.unicorecms.unicoreconnect.bukkit.CommandManager.msg("unicoreconnect.event_buy_donate", replacements = arrayOf("{name}", event.payload.group.name)))
    }
}