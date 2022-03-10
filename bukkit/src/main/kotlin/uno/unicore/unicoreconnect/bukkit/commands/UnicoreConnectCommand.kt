package uno.unicore.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import org.bukkit.command.CommandSender
import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.services.donate.DonateGroupService

@CommandAlias("unicoreconnect|uc")
class UnicoreConnectCommand : BaseCommand() {
    @CommandPermission("unicoreconnect.admin.sync")
    @Subcommand("sync")
    fun sync (sender: CommandSender) {
        try {
            sender.sendMessage("Syncing UnicoreCMS donate-groups...")
            UnicoreCommon.donateGroupService.load()
            sender.sendMessage("Loaded ${DonateGroupService.groups.size} donate-groups from API")
        } catch (e: Exception) {
            sender.sendMessage("Error getting group list! $e")
        }
    }
}