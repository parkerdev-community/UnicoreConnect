package ru.zirconiamc.zirconiaconnect.bukkit.vault.registry

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import ru.zirconiamc.zirconiaconnect.bukkit.PluginInstance
import ru.zirconiamc.zirconiaconnect.bukkit.vault.VaultEconomyHook


class VaultHookRegistry {
    private val plugin = PluginInstance.plugin
    private val logger = plugin.logger

    fun register(): VaultHookRegistry {
        Bukkit.getServer().servicesManager
            .register(
                Economy::class.java,
                VaultEconomyHook(),
                plugin,
                ServicePriority.Highest
            )
        logger.info("Successfully hook Vault")
        return this
    }
}