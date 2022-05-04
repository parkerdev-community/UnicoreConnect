package ru.unicorecms.unicoreconnect.bukkit

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.unicorecms.unicoreconnect.bukkit.commands.PlaytimeCommand
import ru.unicorecms.unicoreconnect.bukkit.commands.ShowcaseCommand
import ru.unicorecms.unicoreconnect.bukkit.commands.UnicoreConnectCommand
import ru.unicorecms.unicoreconnect.bukkit.config.UnicorePluginConfig
import ru.unicorecms.unicoreconnect.bukkit.hooks.BanManager
import ru.unicorecms.unicoreconnect.bukkit.hooks.LuckPerms
import ru.unicorecms.unicoreconnect.bukkit.hooks.vault.Vault
import ru.unicorecms.unicoreconnect.bukkit.listeners.SocketListener
import ru.unicorecms.unicoreconnect.bukkit.tasks.PlaytimeTask
import ru.unicorecms.unicoreconnect.common.SocketClient
import ru.unicorecms.unicoreconnect.common.UnicoreCommon


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

    override fun onEnable() {
        logger.info("Checking server...")
        val gameServer = UnicoreCommon.serversService.check(logger)

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
            luckPerms.hook()

            CommandManager.manager.registerCommand(PlaytimeCommand())
            CommandManager.manager.registerCommand(UnicoreConnectCommand())
            CommandManager.manager.registerCommand(ShowcaseCommand())

            Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                { playtimeTask.handler() }, 0, 20 * 60
            )
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                { socketClient.reconnectHandler() }, 20 * 10, 20 * 3
            )
        }
    }

    override fun onDisable() {
        banManger.unhook()
        vault.unhook()

        socketListener.unregister()
        socketClient.close()
    }
}