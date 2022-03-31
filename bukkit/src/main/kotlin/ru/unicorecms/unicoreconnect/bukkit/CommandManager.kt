package ru.unicorecms.unicoreconnect.bukkit

import co.aikar.commands.BukkitCommandIssuer
import co.aikar.commands.BukkitCommandManager
import co.aikar.commands.MessageType
import co.aikar.locales.MessageKey
import org.bukkit.Bukkit
import java.util.*

class CommandManager {
    private val plugin = ru.unicorecms.unicoreconnect.bukkit.PluginInstance.Companion.plugin
    companion object {
        lateinit var manager: BukkitCommandManager
        private lateinit var consoleIssuer: BukkitCommandIssuer

        fun msg(key: String, type: MessageType = MessageType.INFO, vararg replacements: String): String {
            return ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager.formatMessage(ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.consoleIssuer, type, MessageKey.of(key), *replacements)
        }
    }

    fun init() {
        ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager = BukkitCommandManager(plugin)
        ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.consoleIssuer = ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager.getCommandIssuer(Bukkit.getConsoleSender())

        // Locales
        ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager.locales.defaultLocale = Locale("ru");
        ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager.locales.addMessageBundles("unicoreconnect");

        plugin.logger.info("Locale manager loaded (${ru.unicorecms.unicoreconnect.bukkit.CommandManager.Companion.manager.locales.defaultLocale})")
    }
}
