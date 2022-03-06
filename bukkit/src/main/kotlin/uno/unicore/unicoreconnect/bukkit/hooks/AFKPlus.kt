package uno.unicore.unicoreconnect.bukkit.hooks

import net.lapismc.afkplus.api.AFKPlusPlayerAPI
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import uno.unicore.unicoreconnect.bukkit.PluginInstance

class AFKPlus {
    var playerApi: AFKPlusPlayerAPI? = null
    private val plugin = PluginInstance.plugin
    private fun getAfkPlus(): Plugin? {
        return Bukkit.getPluginManager().getPlugin("AFKPlus")
    }

    fun inject() {
        val afkPlugin = getAfkPlus()
        if (afkPlugin != null) {
            plugin.logger.info("Registered AFKPlus PlayerAPI")
            playerApi = AFKPlusPlayerAPI()
        }
    }
}