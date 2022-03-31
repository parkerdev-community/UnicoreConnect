package ru.unicorecms.unicoreconnect.bukkit

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class PluginInstance(javaPlugin: JavaPlugin) {
    companion object {
        lateinit var plugin: JavaPlugin
        lateinit var version: String
        lateinit var CraftItemStack: Class<*>
    }

    init {
        plugin = javaPlugin
        version = Bukkit.getServer().javaClass.getPackage().name.split("\\.".toRegex()).toTypedArray()[3]
        CraftItemStack = Class.forName("org.bukkit.craftbukkit.$version.inventory.CraftItemStack")
    }
}