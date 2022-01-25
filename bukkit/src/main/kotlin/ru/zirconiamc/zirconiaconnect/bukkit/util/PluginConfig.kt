package ru.zirconiamc.zirconiaconnect.bukkit.util

import org.bukkit.configuration.file.FileConfiguration

class PluginConfig {
    private val plugin = Instance.plugin
    private val config: FileConfiguration = plugin.config

    init {
        setDefaultValues()
    }

    private fun setDefaultValues() {
        /**
         * Do NOT type any sensitive data here!
         * These are only default values.
         * Use the created config.yml
         */
        config.options().header("ZirconiaConnect - конфигурация плагина")
        config.addDefault("server", "hitech")
        config.addDefault("database.jdbc", "jdbc:mysql://127.0.0.1:3306/web?autoReconnect=true&useSSL=false&characterEncoding=UTF-8")
        config.addDefault("database.user", "root")
        config.addDefault("database.password", "XXX")
        save()
    }

    private fun save() {
        config.options().copyDefaults(true)
        plugin.saveConfig()
    }
}