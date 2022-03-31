package ru.unicorecms.unicoreconnect.bukkit.hooks.papi

import org.bukkit.Bukkit
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance

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