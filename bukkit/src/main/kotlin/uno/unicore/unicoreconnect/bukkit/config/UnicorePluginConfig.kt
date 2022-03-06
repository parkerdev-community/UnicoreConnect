package uno.unicore.unicoreconnect.bukkit.config

import org.bukkit.configuration.file.FileConfiguration
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.common.config.UnicoreConfig

class UnicorePluginConfig {
    private val plugin = PluginInstance.plugin
    private val fileConfig: FileConfiguration = plugin.config
    private val config = UnicoreConfig()

    private fun setDefaultValues() {
        fileConfig.options().header("UnicoreConnect - конфигурация плагина")
        fileConfig.addDefault("server", "hitech")
        fileConfig.addDefault("api.url", "http://127.0.0.1:5000")
        fileConfig.addDefault("api.key", "XXX")

        // Messages
        fileConfig.addDefault("messages.not-player", "§4Only players are supported for this command")
        save()
    }

    private fun save() {
        fileConfig.options().copyDefaults(true)
        plugin.saveConfig()
    }

    init {
        setDefaultValues()
        config.server = plugin.config.getString("server").toString()
        config.apiUrl = plugin.config.getString("api.url").toString()
        config.apiKey = plugin.config.getString("api.key").toString()
    }

    fun get(): UnicoreConfig = config
}