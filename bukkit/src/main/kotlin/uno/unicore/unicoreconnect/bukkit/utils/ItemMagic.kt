package uno.unicore.unicoreconnect.bukkit.utils

import de.tr7zw.changeme.nbtapi.NBTItem
import de.tr7zw.changeme.nbtapi.utils.MinecraftVersion
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import uno.unicore.unicoreconnect.bukkit.PluginInstance
import uno.unicore.unicoreconnect.common.types.StoreRequest

class ItemMagic {
    private val plugin = PluginInstance.plugin

    init {
        // Load library...
        MinecraftVersion.disableBStats()
        MinecraftVersion.disableUpdateCheck()
        MinecraftVersion.replaceLogger(plugin.logger)
        NBTItem.convertItemtoNBT(ItemStack(Material.AIR))
    }

    fun webToItem() {
//        val test = NBTContainer("{\"id\": \"$id\", \"Damage\":$damage, \"tag\":$nbt}")
//        test.setInteger("Count", 1)
////        test.setByteArray("tag", UnicoreCommon.gson.fromJson(nbt, ))
//        //test.setObject("tag", nbt)
//
//        println(container)
//        println(test)
//
//        return test
    }

    fun itemToWeb(item: ItemStack, price: Double): StoreRequest {
        val container = NBTItem.convertItemtoNBT(item)
        var id = container.getString("id")
        val nbt = container.getCompound("tag")?.toString()

        if (id == "minecraft:air")
            throw Exception("AIR not supported!")

        if (container.hasKey("Damage")) {
            val damage = container.getInteger("Damage")

            if (damage != 0)
                id = "$id@$damage"
        }

        return StoreRequest(id, nbt, price)
    }

    fun rejectMapper() {

    }

    fun giveMapper() {

    }
}