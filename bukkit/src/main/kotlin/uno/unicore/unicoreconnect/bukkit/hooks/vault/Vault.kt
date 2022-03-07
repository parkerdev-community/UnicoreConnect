package uno.unicore.unicoreconnect.bukkit.hooks.vault

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.ServicePriority
import uno.unicore.unicoreconnect.bukkit.CommandManager
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.bukkit.commands.MoneyCommand

class Vault {
    var enabled: Boolean = false
    private val plugin = PluginInstance.plugin
    private val logger = plugin.logger
    private val vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault")

    companion object {
        var provider: Economy? = null
    }

    fun hook() {
        if (vaultPlugin != null && !enabled) {
            Bukkit.getServer().servicesManager.register(
                Economy::class.java,
                VaultEconomyHook(),
                plugin,
                ServicePriority.Highest
            )
            provider = plugin.server.servicesManager.getRegistration(Economy::class.java)?.provider
            CommandManager.manager.registerCommand(MoneyCommand())
            logger.info("Successfully hook Vault")
            enabled = true
        }
    }

    fun unhook() {
        if (vaultPlugin != null && enabled) {
            provider?.let { Bukkit.getServer().servicesManager.unregister(it) }
            CommandManager.manager.unregisterCommand(MoneyCommand())
            logger.info("Unhook Vault...")
            enabled = false
        }
    }
}