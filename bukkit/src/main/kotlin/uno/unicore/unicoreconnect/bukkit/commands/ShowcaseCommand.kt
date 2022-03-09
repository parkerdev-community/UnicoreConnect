package uno.unicore.unicoreconnect.bukkit.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import net.minecraft.server.v1_12_R1.Item
//import net.minecraft.server.v1_12_R1.ItemStack
import net.minecraft.server.v1_12_R1.MinecraftKey
//import net.minecraft.server.v1_12_R1.NBTTagCompound
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.entity.Player
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.common.UnicoreCommon
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object

// import org.bukkit.nms


@CommandPermission("unicoreconnect.command.showcase")
@CommandAlias("showcase|cart")
class ShowcaseCommand : BaseCommand() {
    @Subcommand("create")
    @CommandPermission("unicoreconnect.command.showcase.add")
    fun add(player: Player) {
        val item = player.inventory.itemInHand
        val nbtTagCompound = Class.forName("net.minecraft.server.${PluginInstance.version}.NBTTagCompound")
//


        val itemStack = PluginInstance.CraftItemStack
            .getDeclaredMethod("asNMSCopy", org.bukkit.inventory.ItemStack::class.java)
            .invoke(null, item)

        val test = Class.forName("net.minecraft.server.${PluginInstance.version}.ItemStack")
            .getMethod("func_190916_E")
            .invoke(itemStack)

        println(test)

        //println(Item.REGISTRY.b(some.javaClass.getMethod("getItem", null).invoke(null) as Item))
////
//        println(some.javaClass.fields.)
//        val sometwo = some.getMethod("save", nbtTagCompound::class.java)
//            .invoke(null, nbtTagCompound.getConstructor().newInstance())

//            .getMethod("save", nbtTagCompound::class.java)
//            .invoke(null, nbtTagCompound.getConstructor().newInstance()).javaClass

//  val itemId = CraftItemStack.asNMSCopy(item)
////        val itemStack = CraftItemStack.asNewCraftStack(Item.REGISTRY.get(MinecraftKey(itemId)))
////
//        println(Item.REGISTRY.b(itemId.item))
        //println(itemStack.type)

        //println(PluginInstance.CraftItemStack.getMethod("asNMSCopy", org.bukkit.inventory.ItemStack::class.java).invoke(item))
    }
}
