package ru.zirconiamc.zirconiaconnect.bukkit.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.zirconiamc.zirconiaconnect.bukkit.PluginInstance

class MoneyCommand : CommandExecutor {
    private val plugin = PluginInstance.plugin
    private val config = plugin.config

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(config.getString("messages.not-player"))
            return true
        }

        return true
    }
}