package ru.zirconiamc.zirconiaconnect.bukkit.util

import org.bukkit.plugin.java.JavaPlugin

class Instance(javaPlugin: JavaPlugin) {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = javaPlugin
    }
}