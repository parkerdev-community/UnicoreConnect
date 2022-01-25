package ru.zirconiamc.zirconiaconnect.bukkit

import org.bukkit.plugin.java.JavaPlugin
import ru.zirconiamc.zirconiaconnect.bukkit.config.HikariPluginConfig
import ru.zirconiamc.zirconiaconnect.bukkit.util.Instance
import ru.zirconiamc.zirconiaconnect.bukkit.util.PluginConfig
import ru.zirconiamc.zirconiaconnect.common.database.Initializer
import ru.zirconiamc.zirconiaconnect.common.database.ModelInitializer

class ZirconiaConnect: JavaPlugin() {
    private val instance = Instance(this)
    private val config = PluginConfig()
    private val hikariConfig = HikariPluginConfig()
    private val database = Initializer(hikariConfig.get())
    private val databaseModel = ModelInitializer()

    override fun onEnable() {
        database.connect()
        databaseModel.createSchema()
        logger.info("Database connection established")
    }
}