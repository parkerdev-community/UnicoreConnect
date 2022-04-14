package ru.unicorecms.unicoreconnect.bukkit.config

import hazae41.minecraft.kutils.bukkit.keys
import org.bukkit.configuration.file.FileConfiguration
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.config.UnicoreConfig

class UnicorePluginConfig {
    private val plugin = PluginInstance.plugin
    private val fileConfig: FileConfiguration = plugin.config
    private val config = UnicoreConfig()

    private fun setDefaultValues() {
        fileConfig.options().header("UnicoreConnect - конфигурация плагина")
        fileConfig.addDefault("server", "hitech")
        fileConfig.addDefault("api.url", "http://127.0.0.1:5000")
        fileConfig.addDefault("api.key", "XXX")
        fileConfig.addDefault("items_mapping", UnicoreCommon.itemsMapDefault)
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

        val mapItem = plugin.config.getConfigurationSection("items_mapping")

        for (key in mapItem!!.keys) {
            UnicoreCommon.itemsMap[key] = mapItem[key].toString()
        }
    }

    fun get(): UnicoreConfig = config
}