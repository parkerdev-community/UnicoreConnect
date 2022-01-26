package ru.zirconiamc.zirconiaconnect.bukkit.listeners

import me.confuser.banmanager.bukkit.api.events.PlayerBannedEvent
import me.confuser.banmanager.bukkit.api.events.PlayerUnbanEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import ru.zirconiamc.zirconiaconnect.common.services.BanService


class BanListener : Listener {
    private val banService = BanService()



    @EventHandler
    fun banEvent(event: PlayerBannedEvent) {
        banService.ban(event.ban)
    }

    @EventHandler
    fun unbanEvent(event: PlayerUnbanEvent) {
        banService.unban(event.ban)
    }
}