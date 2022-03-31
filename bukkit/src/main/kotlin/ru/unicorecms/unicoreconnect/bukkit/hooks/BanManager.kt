package ru.unicorecms.unicoreconnect.bukkit.hooks

import me.confuser.banmanager.bukkit.listeners.JoinListener
import me.confuser.banmanager.common.BanManagerPlugin
import org.bukkit.Bukkit
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.bukkit.listeners.BanJoinListener
import ru.unicorecms.unicoreconnect.bukkit.listeners.BanListener

class BanManager {
    var enabled: Boolean = false
    private val plugin = PluginInstance.plugin
    private val bmPlugin = Bukkit.getPluginManager().getPlugin("BanManager")
    
    fun hook() {
        if (bmPlugin != null && !enabled) {
            AsyncPlayerPreLoginEvent.getHandlerList().unregister(bmPlugin);
            PlayerJoinEvent.getHandlerList().unregister(bmPlugin);
            plugin.server.pluginManager.registerEvents(BanJoinListener(BanManagerPlugin.getInstance()), plugin)
            plugin.server.pluginManager.registerEvents(BanListener(), plugin)

            plugin.logger.info("Successfully hook BanManger")
            enabled = true
        }
    }

    fun unhook() {
        if (bmPlugin != null && enabled) {
            plugin.server.pluginManager.registerEvents(JoinListener(BanManagerPlugin.getInstance()), bmPlugin)
            plugin.logger.info("Unhook BanManger...")
            enabled = false
        }
    }
}