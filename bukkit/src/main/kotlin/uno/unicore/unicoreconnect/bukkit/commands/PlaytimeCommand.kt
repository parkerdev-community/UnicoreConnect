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
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import uno.unicore.unicoreconnect.bukkit.CommandManager
import uno.unicore.unicoreconnect.bukkit.hooks.vault.Vault
import uno.unicore.unicoreconnect.common.UnicoreCommon


@CommandPermission("unicoreconnect.command.playtime")
@CommandAlias("playtime|pt")
class PlaytimeCommand : BaseCommand() {
    val formatter = PeriodFormatterBuilder()
        .appendDays()
        .appendSuffix("d ")
        .appendHours()
        .appendSuffix("h ")
        .appendMinutes()
        .appendSuffix("m ")
        .toFormatter()

    @Default
    fun main(@Optional player: Player?, @Optional target: OnlinePlayer?) {
        if (player == null) {
            if (target == null)
                Bukkit.getConsoleSender()
                    .sendMessage(CommandManager.msg("acf-core.not_allowed_on_console", type = MessageType.ERROR))
            else {
                val resp = UnicoreCommon.playtimeService.findOne(target.player.uniqueId)
                Bukkit.getConsoleSender().sendMessage(
                    CommandManager.msg(
                        "unicoreconnect.command_playtime_other",
                        replacements = arrayOf("{player}", resp.user.username, "{server}", UnicoreCommon.server!!.name, "{time}", formatter.print(Duration(resp.time * 60 * 1000).toPeriod()))
                    )
                )
            }
        } else {
            if (target == null) {
                val resp = UnicoreCommon.playtimeService.findOne(player.uniqueId)
                player.sendMessage(
                    CommandManager.msg(
                        "unicoreconnect.command_playtime",
                        replacements = arrayOf( "{server}", UnicoreCommon.server!!.name, "{time}", formatter.print(Duration(resp.time * 60 * 1000).toPeriod()))
                    )
                )
            } else {
                if (player.hasPermission("unicoreconnect.command.playtime.other")) {
                    val resp = UnicoreCommon.playtimeService.findOne(target.player.uniqueId)
                    player.sendMessage(
                        CommandManager.msg(
                            "unicoreconnect.command_playtime_other",
                            replacements = arrayOf("{player}", resp.user.username, "{server}", UnicoreCommon.server!!.name, "{time}", formatter.print(Duration(resp.time * 60 * 1000).toPeriod()))
                        )
                    )
                } else {
                    player.sendMessage(CommandManager.msg("acf-core.permission_denied", type = MessageType.ERROR))
                }
            }
        }
    }
}