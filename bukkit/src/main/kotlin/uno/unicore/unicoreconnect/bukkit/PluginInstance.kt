package uno.unicore.unicoreconnect.bukkit

import co.aikar.commands.BukkitLocales
import org.bukkit.plugin.java.JavaPlugin


class PluginInstance(javaPlugin: JavaPlugin) {
    companion object {
        lateinit var plugin: JavaPlugin
        lateinit var locales: BukkitLocales
    }

    init {
        plugin = javaPlugin
    }
}