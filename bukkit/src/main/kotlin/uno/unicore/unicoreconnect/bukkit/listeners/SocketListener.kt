package uno.unicore.unicoreconnect.bukkit.listeners

import org.bukkit.Bukkit
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import uno.unicore.unicoreconnect.bukkit.CommandManager
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.events.SocketEvent

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
        // Сервер проверяется в COMMON
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(CommandManager.msg("unicoreconnect.event_buy_donate", replacements = arrayOf("{group}", event.payload.group.name)))
    }
}