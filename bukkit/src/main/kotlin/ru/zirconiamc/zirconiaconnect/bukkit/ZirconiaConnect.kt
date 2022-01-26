package ru.zirconiamc.zirconiaconnect.bukkit

import org.bukkit.plugin.java.JavaPlugin
import ru.zirconiamc.zirconiaconnect.bukkit.commands.MoneyCommand
import ru.zirconiamc.zirconiaconnect.bukkit.config.HikariPluginConfig
import ru.zirconiamc.zirconiaconnect.bukkit.config.ZirconiaPluginConfig
import ru.zirconiamc.zirconiaconnect.bukkit.listeners.BanListener
import ru.zirconiamc.zirconiaconnect.bukkit.listeners.PlayerJoinListener
import ru.zirconiamc.zirconiaconnect.bukkit.vault.registry.VaultHookRegistry
import ru.zirconiamc.zirconiaconnect.common.ZirconiaInstance
import ru.zirconiamc.zirconiaconnect.common.database.Initializer
import ru.zirconiamc.zirconiaconnect.common.database.ModelInitializer

class ZirconiaConnect: JavaPlugin() {
    private val instance = PluginInstance(this)
    private val config = PluginConfig()
    private val hikariConfig = HikariPluginConfig()
    private val database = Initializer(hikariConfig.get())
    private val databaseModel = ModelInitializer()
    private val zirconiaPluginConfig = ZirconiaPluginConfig()
    private val zirconiaInstance = ZirconiaInstance(zirconiaPluginConfig.get())
    private val vaultHookRegistry = VaultHookRegistry()

    override fun onEnable() {
        database.connect()
        databaseModel.createSchema()
        logger.info("Database connection established")
        vaultHookRegistry.register()

        // Listeners
        server.pluginManager.registerEvents(BanListener(), this)
        server.pluginManager.registerEvents(PlayerJoinListener(), this)
    }
}