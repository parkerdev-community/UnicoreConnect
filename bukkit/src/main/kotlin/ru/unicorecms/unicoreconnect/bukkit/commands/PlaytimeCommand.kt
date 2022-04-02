package ru.unicorecms.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.bukkit.CommandManager

@CommandAlias("playtime|pt")
class PlaytimeCommand : BaseCommand() {
    private val formatter = PeriodFormatterBuilder()
        .appendDays()
        .appendSuffix("d ")
        .appendHours()
        .appendSuffix("h ")
        .appendMinutes()
        .appendSuffix("m ")
        .toFormatter()

    @Default
    @CommandPermission("unicoreconnect.command.playtime")
    fun main(player: Player) {
        val resp = UnicoreCommon.playtimeService.findOne(player.uniqueId)

        player.sendMessage(
            CommandManager.msg(
                "unicoreconnect.command_playtime",
                replacements = arrayOf(
                    "{server}",
                    UnicoreCommon.server!!.name,
                    "{time}",
                    formatter.print(Duration(resp.time * 60 * 1000).toPeriod())
                )
            )
        )
    }

    @Subcommand("top")
    @CommandPermission("unicoreconnect.command.playtime.top")
    fun top(sender: CommandSender) {
        val resp = UnicoreCommon.playtimeService.findTop()
        sender.sendMessage(
            CommandManager.msg(
                "unicoreconnect.command_playtime_top",
                replacements = arrayOf( "{server}", UnicoreCommon.server!!.name, "{rows}", resp.mapIndexed { index, playtime -> "${index + 1}.${playtime.user.username} - ${formatter.print(Duration(playtime.time * 60 * 1000).toPeriod())}" }.joinToString("\n"))
            )
        )
    }
}