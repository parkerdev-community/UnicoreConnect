package ru.unicorecms.unicoreconnect.bukkit

import hazae41.minecraft.kutils.bukkit.schedule
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import ru.unicorecms.unicoreconnect.bukkit.config.UnicorePluginConfig
import ru.unicorecms.unicoreconnect.bukkit.hooks.BanManager
import ru.unicorecms.unicoreconnect.bukkit.hooks.vault.Vault
import ru.unicorecms.unicoreconnect.bukkit.listeners.SocketListener
import ru.unicorecms.unicoreconnect.common.SocketClient
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import java.util.concurrent.TimeUnit
import ru.unicorecms.unicoreconnect.bukkit.commands.PlaytimeCommand
import ru.unicorecms.unicoreconnect.bukkit.commands.ShowcaseCommand
import ru.unicorecms.unicoreconnect.bukkit.commands.UnicoreConnectCommand
import ru.unicorecms.unicoreconnect.bukkit.hooks.LuckPerms
import ru.unicorecms.unicoreconnect.bukkit.hooks.papi.ExpansionHook

@Suppress("unused")
class UnicoreConnectBukkit : JavaPlugin() {
    // Reflect instances
    private val pluginInstance = PluginInstance(this)
    private val unicoreConfig = UnicorePluginConfig().get()
    private val unicoreCommon = UnicoreCommon(unicoreConfig)
    private val commandManager = CommandManager()

    private val socketClient = SocketClient(logger)
    private val vault = Vault()
    private val socketListener = SocketListener()
    private val banManger = BanManager()
    private val luckPerms = LuckPerms()
    private val playtimeTask = PlaytimeTask()
    private val expansionHook = ExpansionHook()

    private var reconnectScheduler: BukkitTask? = null
    private var playtimeScheduler: BukkitTask? = null

    override fun onEnable() {
        logger.info("Checking server...")
        val gameServer = UnicoreCommon.serversService.check()

        if (gameServer == null) {
            logger.warning("Server with id '${UnicoreCommon.config.server}' not found in UnicoreCMS or ApiUrl incorrect!")
        } else {
            UnicoreCommon.server = gameServer
            logger.info("Successfully received ${gameServer.name} [#${gameServer.id}] server from UnicoreCMS")
            logger.info("Starting WebSocket client...")

            commandManager.init()
            socketListener.register()
            socketClient.connect()

            banManger.hook()
            vault.hook()
            playtimeTask.load()
            expansionHook.hook()
            luckPerms.hook()

            CommandManager.manager.registerCommand(PlaytimeCommand())
            CommandManager.manager.registerCommand(UnicoreConnectCommand())
            CommandManager.manager.registerCommand(ShowcaseCommand())

            reconnectScheduler = schedule(period = 1, unit = TimeUnit.MINUTES) { playtimeTask.handler() }
            playtimeScheduler = schedule(period = 3, delay = 10) { socketClient.reconnectHandler() }
        }
    }

    override fun onDisable() {
        reconnectScheduler?.cancel()
        playtimeScheduler?.cancel()

        banManger.unhook()
        vault.unhook()
        expansionHook.unhook()

        socketListener.unregister()
        socketClient.close()
    }
}