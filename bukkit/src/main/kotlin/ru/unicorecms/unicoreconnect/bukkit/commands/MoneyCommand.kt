package ru.unicorecms.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.MessageType
import co.aikar.commands.annotation.*
import co.aikar.commands.bukkit.contexts.OnlinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.unicorecms.unicoreconnect.bukkit.hooks.vault.Vault
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.bukkit.CommandManager

@CommandPermission("unicoreconnect.command.money")
@CommandAlias("money|bal|balance")
class MoneyCommand : BaseCommand() {
    @Default
    fun main(player: Player) {
        val resp = UnicoreCommon.moneyService.findOne(player.uniqueId)

        player.sendMessage(
            CommandManager.msg(
                "unicoreconnect.command_money",
                replacements = arrayOf( "{server}", UnicoreCommon.server!!.name, "{money}", Vault.provider!!.format(resp.money))
            )
        )
    }

    @Subcommand("pay")
    @Syntax("[player] [amount]")
    @CommandPermission("unicoreconnect.command.money.pay")
    fun pay(player: Player, target: OnlinePlayer, amount: Double) {
        try {
            val resp = UnicoreCommon.moneyService.transfer(player.uniqueId, player.address.toString(), target.player.uniqueId, amount)
            player.sendMessage(
                CommandManager.msg(
                    "unicoreconnect.command_money_pay",
                    replacements = arrayOf( "{amount}", Vault.provider!!.format(amount), "{money}", Vault.provider!!.format(resp.money), "{target}", target.player.name)
                )
            )
            target.player.sendMessage(CommandManager.msg("unicoreconnect.command_money_pay_target", replacements = arrayOf( "{amount}", Vault.provider!!.format(amount), "{player}", player.name)))
        } catch (_: Exception) {
            player.sendMessage(CommandManager.msg("unicoreconnect.command_money_pay_fail", type = MessageType.ERROR))
        }
    }

    @Subcommand("top")
    @CommandPermission("unicoreconnect.command.money.top")
    fun top(sender: CommandSender) {
        val resp = UnicoreCommon.moneyService.top()
        sender.sendMessage(
            CommandManager.msg(
                "unicoreconnect.command_money_top",
                replacements = arrayOf( "{server}", UnicoreCommon.server!!.name, "{rows}", resp.mapIndexed { index, money -> "${index + 1}.${money.user.username} - ${Vault.provider!!.format(money.money)}" }.joinToString("\n"))
            )
        )
    }
}