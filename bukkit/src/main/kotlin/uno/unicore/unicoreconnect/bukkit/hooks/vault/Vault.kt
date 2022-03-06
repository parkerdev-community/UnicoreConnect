package uno.unicore.unicoreconnect.bukkit.hooks.vault

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.ServicePriority
import uno.unicore.unicoreconnect.bukkit.PluginInstance

class Vault {
    var enable: Boolean = false
    private val plugin = PluginInstance.plugin
    private val logger = plugin.logger
    private val vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault")

    fun hook() {
        if (vaultPlugin != null) {
            Bukkit.getServer().servicesManager.register(
                Economy::class.java,
                VaultEconomyHook(),
                plugin,
                ServicePriority.Highest
            )
            logger.info("Successfully hook Vault")
            enable = true
        }
    }

    fun unhook() {
        if (vaultPlugin != null) {
            Bukkit.getServer().servicesManager.unregister(this)
            enable = false
        }
    }
}