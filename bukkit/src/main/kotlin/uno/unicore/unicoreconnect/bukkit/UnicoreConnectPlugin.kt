package uno.unicore.unicoreconnect.bukkit

import hazae41.minecraft.kutils.bukkit.schedule
import org.bukkit.plugin.java.JavaPlugin
import uno.unicore.unicoreconnect.bukkit.config.UnicorePluginConfig
import uno.unicore.unicoreconnect.bukkit.hooks.AFKPlus
import uno.unicore.unicoreconnect.bukkit.hooks.vault.Vault
import uno.unicore.unicoreconnect.common.SocketClient
import uno.unicore.unicoreconnect.common.UnicoreCommon
import java.util.concurrent.TimeUnit
import uno.unicore.unicoreconnect.bukkit.hooks.BanManager
import uno.unicore.unicoreconnect.bukkit.listeners.SocketListener

@Suppress("unused")
class UnicoreConnectPlugin : JavaPlugin() {
    // Reflect instances
    private val pluginInstance = PluginInstance(this)
    private val unicoreCommon = UnicoreCommon(UnicorePluginConfig().get())

    private val socketClient = SocketClient()
    private val vault = Vault()
    private val socketListener = SocketListener()
    private var banManger = BanManager()
    private val afkPlus = AFKPlus()

    override fun onEnable() {
        logger.info("Checking server...")
        val gameServer = UnicoreCommon.serversService.check()

        if (gameServer == null) {
            logger.warning("Server with id '${UnicoreCommon.config.server}' not found in UnicoreCMS or ApiUrl incorrect!")
        } else {
            logger.info("Successfully received ${gameServer.name} [#${gameServer.id}] server from UnicoreCMS")
            logger.info("Starting WebSocket client...")

            socketListener.register()
            socketClient.connect()

            banManger.hook()
            vault.hook()
            afkPlus.inject()

            // Schedulers
            schedule(period = 3, delay = 10) {
                socketClient.reconnectHandler()
            }

            schedule(period = 1, unit = TimeUnit.MINUTES) {
                if (server.onlinePlayers.isNotEmpty()) {
                    if (afkPlus.playerApi == null) {
                        UnicoreCommon.playtimeService.update(server.onlinePlayers
                            .map { it.uniqueId }
                        )
                    } else {
                        UnicoreCommon.playtimeService.update(server.onlinePlayers
                            .filter { !afkPlus.playerApi!!.getPlayer(it.uniqueId).isAFK }
                            .map { it.uniqueId }
                        )
                    }
                }
            }
        }
    }

    override fun onDisable() {
        BanManager().unhook()
        vault.unhook()

        logger.info("Closing WebSocket connection...")
        socketListener.unregister()
        socketClient.close()
    }
}