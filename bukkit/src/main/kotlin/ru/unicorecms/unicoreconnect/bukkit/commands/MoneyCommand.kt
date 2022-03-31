package ru.unicorecms.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import org.bukkit.entity.Player
import ru.unicorecms.unicoreconnect.bukkit.hooks.vault.Vault
import ru.unicorecms.unicoreconnect.common.UnicoreCommon

@CommandPermission("unicoreconnect.command.money")
@CommandAlias("money|bal|balance")
class MoneyCommand : BaseCommand() {
    @Default
    fun main(player: Player) {
        val resp = UnicoreCommon.moneyService.findOne(player.uniqueId)

        player.sendMessage(
            ru.unicorecms.unicoreconnect.bukkit.CommandManager.msg(
                "unicoreconnect.command_money",
                replacements = arrayOf( "{server}", UnicoreCommon.server!!.name, "{money}", Vault.provider!!.format(resp.money))
            )
        )
    }
}