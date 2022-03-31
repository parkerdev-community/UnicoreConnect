package ru.unicorecms.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.MessageType
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import co.aikar.commands.annotation.Syntax
import org.bukkit.entity.Player
import ru.unicorecms.unicoreconnect.bukkit.utils.ItemMagic
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.WarehouseItem
import ru.unicorecms.unicoreconnect.bukkit.CommandManager

@CommandPermission("unicoreconnect.command.showcase")
@CommandAlias("showcase|cart")
class ShowcaseCommand : BaseCommand() {
    private val itemMagic = ItemMagic()

    @Subcommand("create")
    @Syntax("[price] [name]")
    @CommandPermission("unicoreconnect.command.showcase.create")
    fun create(player: Player, price: Double, name: String) {
        val data = itemMagic.serialize(player.inventory.itemInHand, name, price)
        val req = UnicoreCommon.showcaseService.create(data)

        if (req)
            player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_create", replacements = arrayOf("{id}", data.id)))
        else
            throw Exception()
    }

    @Subcommand("all")
    fun all(player: Player) {
        val gived = arrayListOf<WarehouseItem>()
        val req = UnicoreCommon.showcaseService.find(player.uniqueId)

        if (req.isEmpty())
            return player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_all_fail", type = MessageType.ERROR))

        for (item in req) {
            val itemStack = itemMagic.serialize(item)

            for (i in 1..item.amount) {
                if (player.inventory.addItem(itemStack).size == 0)
                    gived.add(item)
            }
        }

        UnicoreCommon.showcaseService.gived(gived)
        player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase", replacements = arrayOf("{amount}", gived.size.toString())))
    }

    @Subcommand("list")
    fun list(player: Player) {
        val req = UnicoreCommon.showcaseService.find(player.uniqueId)

        if (req.isEmpty())
            return player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_all_fail", type = MessageType.ERROR))

        player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_list", replacements = arrayOf("{items}", req.joinToString(
            "\n"
        ) { "${it.product.name} x${it.amount} (#${it.id})" })))
    }

    @Syntax("[id]")
    @Subcommand("give")
    fun give(player: Player, id: Int) {
        val gived = arrayListOf<WarehouseItem>()
        val req = UnicoreCommon.showcaseService.find(player.uniqueId).filter { it.id == id }

        if (req.isEmpty())
            return player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase_give_fail", replacements = arrayOf("{id}", id.toString()), type = MessageType.ERROR))

        for (item in req) {
            val itemStack = itemMagic.serialize(item)

            for (i in 1..item.amount) {
                if (player.inventory.addItem(itemStack).size == 0)
                    gived.add(item)
            }
        }

        UnicoreCommon.showcaseService.gived(gived)
        player.sendMessage(CommandManager.msg("unicoreconnect.command_showcase", replacements = arrayOf("{amount}", gived.size.toString())))
    }
}
