package ru.unicorecms.unicoreconnect.bukkit.hooks

import org.bukkit.Bukkit
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.bukkit.listeners.LPJoinListener
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.services.donate.DonateGroupService
import ru.unicorecms.unicoreconnect.common.services.donate.DonatePermissionService

class LuckPerms {
    var enabled: Boolean = false
    private val plugin = PluginInstance.plugin
    private val lpPlugin = Bukkit.getPluginManager().getPlugin("LuckPerms")

    fun hook() {
        if (lpPlugin != null && !enabled) {
            try {
                plugin.logger.info("Syncing UnicoreCMS donate-groups...")
                plugin.logger.info("Syncing UnicoreCMS donate-perms...")

                UnicoreCommon.donateGroupService.load()
                UnicoreCommon.donatePermissionService.load()

                plugin.logger.info("Loaded ${DonateGroupService.groups.size} donate-groups from API")
                plugin.logger.info("Loaded ${DonatePermissionService.permissions.size} donate-perms from API")

                plugin.server.pluginManager.registerEvents(LPJoinListener(), plugin)

                plugin.logger.info("Successfully hook LuckPerms")
                enabled = true
            } catch (e: Exception) {
                plugin.logger.warning("Error getting group list! $e")
            }
        }
    }
}