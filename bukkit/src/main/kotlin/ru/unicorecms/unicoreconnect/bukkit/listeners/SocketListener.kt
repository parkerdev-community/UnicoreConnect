package ru.unicorecms.unicoreconnect.bukkit.listeners

import org.bukkit.Bukkit
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import ru.unicorecms.unicoreconnect.common.events.SocketEvent
import ru.unicorecms.unicoreconnect.bukkit.CommandManager

class SocketListener {
    fun register() {
        EventBus.getDefault().register(this)
    }

    fun unregister() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGiveGroup(event: SocketEvent.GIVE_GROUP) {
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(CommandManager.msg("unicoreconnect.event_give_group", replacements = arrayOf("{name}", event.payload.group.name)))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTakeGroup(event: SocketEvent.TAKE_GROUP) {
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(CommandManager.msg("unicoreconnect.event_take_group", replacements = arrayOf("{name}", event.payload.group.name)))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGivePermission(event: SocketEvent.GIVE_PERMISSION) {
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(CommandManager.msg("unicoreconnect.event_give_permission", replacements = arrayOf("{name}", event.payload.permission.name)))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTakePermission(event: SocketEvent.TAKE_PERMISSION) {
        val player = Bukkit.getPlayer(event.payload.user.username)
        player?.sendMessage(CommandManager.msg("unicoreconnect.event_take_permission", replacements = arrayOf("{name}", event.payload.permission.name)))
    }
}