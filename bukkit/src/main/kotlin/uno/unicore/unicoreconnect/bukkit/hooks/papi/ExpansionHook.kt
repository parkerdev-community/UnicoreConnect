package uno.unicore.unicoreconnect.bukkit.hooks.papi

import org.bukkit.Bukkit
import uno.unicore.unicoreconnect.bukkit.PluginInstance

class ExpansionHook {
    private val plugin = PluginInstance.plugin
    private val papiPlugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI")

    fun hook() {
        if (papiPlugin != null) {
            UnicoreExpansion().register();
            plugin.logger.info("Successfully hook PlaceholderAPI")
        }
    }

    fun unhook() {
        if (papiPlugin != null) {
            UnicoreExpansion().unregister()
            plugin.logger.info("Unhook PlaceholderAPI...")
        }
    }
}