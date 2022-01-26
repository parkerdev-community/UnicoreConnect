package ru.zirconiamc.zirconiaconnect.bukkit.config

import ru.zirconiamc.zirconiaconnect.bukkit.PluginInstance
import ru.zirconiamc.zirconiaconnect.common.config.ZirconiaConfiguration

class ZirconiaPluginConfig {
    private val plugin = PluginInstance.plugin
    private val config = ZirconiaConfiguration()

    private val server: String = plugin.config.getString("server")

    init {
        config.server = server
    }

    fun get(): ZirconiaConfiguration = config
}