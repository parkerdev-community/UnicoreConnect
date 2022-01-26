package ru.zirconiamc.zirconiaconnect.bukkit.config

import com.zaxxer.hikari.HikariConfig
import ru.zirconiamc.zirconiaconnect.bukkit.PluginInstance
import ru.zirconiamc.zirconiaconnect.common.config.HikariConfiguration

class HikariPluginConfig {
    private val plugin = PluginInstance.plugin

    private val jdbc: String = plugin.config.getString("database.jdbc")
    private val user: String? = plugin.config.getString("database.user")
    private val password: String? = plugin.config.getString("database.password")

    private val config = HikariConfiguration(this.jdbc, this.user, this.password)

    fun get(): HikariConfig = config.get()
}