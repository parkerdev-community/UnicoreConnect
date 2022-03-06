package uno.unicore.unicoreconnect.bukkit.hooks

import me.confuser.banmanager.bukkit.listeners.JoinListener
import me.confuser.banmanager.common.BanManagerPlugin
import org.bukkit.Bukkit
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.bukkit.listeners.BanJoinListener
import uno.unicore.unicoreconnect.bukkit.listeners.BanListener

class BanManager {
    var enable: Boolean = false
    private val plugin = PluginInstance.plugin
    private val bmPlugin = Bukkit.getPluginManager().getPlugin("BanManager")

    fun hook() {
        if (bmPlugin != null) {
            AsyncPlayerPreLoginEvent.getHandlerList().unregister(bmPlugin);
            PlayerJoinEvent.getHandlerList().unregister(bmPlugin);
            plugin.server.pluginManager.registerEvents(BanJoinListener(BanManagerPlugin.getInstance()), plugin)
            plugin.logger.info("Modified BanManger listeners")

            plugin.server.pluginManager.registerEvents(BanListener(), plugin)
            plugin.logger.info("Registered BanManger bans event listeners")

            enable = true
        }
    }

    fun unhook() {
        if (bmPlugin != null) {
            plugin.server.pluginManager.registerEvents(JoinListener(BanManagerPlugin.getInstance()), bmPlugin)
            plugin.logger.info("Modified BanManger listeners to default")

            enable = false
        }
    }
}