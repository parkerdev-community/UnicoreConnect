package ru.unicorecms.unicoreconnect.bukkit

import co.aikar.commands.BukkitCommandIssuer
import co.aikar.commands.BukkitCommandManager
import co.aikar.commands.MessageType
import co.aikar.locales.MessageKey
import org.bukkit.Bukkit
import java.util.*

class CommandManager {
    private val plugin = PluginInstance.plugin
    companion object {
        lateinit var manager: BukkitCommandManager
        private lateinit var consoleIssuer: BukkitCommandIssuer

        fun msg(key: String, type: MessageType = MessageType.INFO, vararg replacements: String): String {
            return manager.formatMessage(consoleIssuer, type, MessageKey.of(key), *replacements)
        }
    }

    fun init() {
        manager = BukkitCommandManager(plugin)
        consoleIssuer = manager.getCommandIssuer(Bukkit.getConsoleSender())

        // Locales
        manager.locales.defaultLocale = Locale("ru");
        manager.locales.addMessageBundles("unicoreconnect");

        plugin.logger.info("Locale manager loaded (${manager.locales.defaultLocale})")
    }
}
