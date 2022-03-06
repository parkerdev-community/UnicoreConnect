package uno.unicore.unicoreconnect.bukkit

import com.Zrips.CMI.CMI
import com.earth2me.essentials.Essentials
import org.bukkit.Bukkit
import uno.unicore.unicoreconnect.common.UnicoreCommon


class PlaytimeTask {
    private val plugin = PluginInstance.plugin
    private val essPlugin = Bukkit.getPluginManager().getPlugin("Essentials") as Essentials?
    private val cmiPlugin = Bukkit.getPluginManager().getPlugin("CMI")
    private var cmiInstance: CMI? = null

    fun inject() {
        if (essPlugin != null) {
            plugin.logger.info("Essentials/EssentialsX is used to check players for AFK")
        } else if (cmiPlugin != null) {
            cmiInstance = CMI.getInstance()
            plugin.logger.info("CMI is used to check players for AFK")
        }
    }

    fun handler() {
        val players = plugin.server.onlinePlayers

        if (players.isNotEmpty()) {
            if (essPlugin != null) {
                UnicoreCommon.playtimeService.update(players
                    .filter { !essPlugin.getUser(it.uniqueId).isAfk }
                    .map { it.uniqueId }
                )
            } else if (cmiInstance != null) {
                UnicoreCommon.playtimeService.update(players
                    .filter { !cmiInstance!!.playerManager.getUser(it).isAfk }
                    .map { it.uniqueId }
                )
            } else {
                UnicoreCommon.playtimeService.update(players.map { it.uniqueId })
            }
        }
    }
}