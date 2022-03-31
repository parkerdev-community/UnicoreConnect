package ru.unicorecms.unicoreconnect.bukkit.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.unicorecms.unicoreconnect.common.UnicoreCommon

class LPJoinListener : Listener {
    @EventHandler
    fun joinEvent(event: PlayerJoinEvent) {
        UnicoreCommon.donateGroupService.handleJoin(event.player.uniqueId)
        UnicoreCommon.donatePermissionService.handleJoin(event.player.uniqueId)
    }
}