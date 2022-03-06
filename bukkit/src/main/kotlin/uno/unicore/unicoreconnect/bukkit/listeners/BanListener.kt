package uno.unicore.unicoreconnect.bukkit.listeners

import me.confuser.banmanager.bukkit.api.events.PlayerBannedEvent
import me.confuser.banmanager.bukkit.api.events.PlayerUnbanEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import uno.unicore.unicoreconnect.common.UnicoreCommon


class BanListener : Listener {
    @EventHandler
    fun banEvent(event: PlayerBannedEvent) {
        UnicoreCommon.banService.create(event.ban)
    }

    @EventHandler
    fun unbanEvent(event: PlayerUnbanEvent) {
        UnicoreCommon.banService.delete(event.ban)
    }
}