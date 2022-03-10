package uno.unicore.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.entity.Player
import org.joda.time.Duration
import uno.unicore.unicoreconnect.bukkit.CommandManager
import uno.unicore.unicoreconnect.bukkit.utils.ItemMagic
import uno.unicore.unicoreconnect.common.UnicoreCommon


// import org.bukkit.nms


@CommandPermission("unicoreconnect.command.showcase")
@CommandAlias("showcase|cart")
class ShowcaseCommand : BaseCommand() {
    private val itemMagic = ItemMagic()

    @Subcommand("create")
    @CommandPermission("unicoreconnect.command.showcase.add")
    fun create(player: Player, price: Double) {
        val data = itemMagic.itemToWeb(player.inventory.itemInHand, price)
        val req = UnicoreCommon.showcaseService.create(data)

        if (req)
            player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_create", replacements = arrayOf("{id}", data.id )))
        else
            throw Exception()
    }
}
