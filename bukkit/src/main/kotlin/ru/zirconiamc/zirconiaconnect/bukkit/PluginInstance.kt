package ru.zirconiamc.zirconiaconnect.bukkit

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.command.CommandSender
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin


class PluginInstance(javaPlugin: JavaPlugin) {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = javaPlugin
    }
}