package ru.unicorecms.unicoreconnect.bukkit.listeners

import me.confuser.banmanager.common.BanManagerPlugin
import me.confuser.banmanager.bukkit.listeners.JoinListener as BMJoinListener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import ru.unicorecms.unicoreconnect.common.UnicoreCommon

class BanJoinListener(plugin: BanManagerPlugin) : BMJoinListener(plugin) {
    @EventHandler(priority = EventPriority.HIGHEST)
    override fun banCheck(event: AsyncPlayerPreLoginEvent) {
        UnicoreCommon.banService.handleJoin(event.uniqueId)
        super.banCheck(event)
    }
}