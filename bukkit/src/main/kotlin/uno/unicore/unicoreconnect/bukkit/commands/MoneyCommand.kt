package uno.unicore.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.MessageType
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Optional
import co.aikar.commands.bukkit.contexts.OnlinePlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import uno.unicore.unicoreconnect.bukkit.CommandManager
import uno.unicore.unicoreconnect.bukkit.hooks.vault.Vault
import uno.unicore.unicoreconnect.common.UnicoreCommon

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
}