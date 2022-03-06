package uno.unicore.unicoreconnect.bukkit

import org.bukkit.plugin.java.JavaPlugin


class PluginInstance(javaPlugin: JavaPlugin) {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = javaPlugin
    }
}