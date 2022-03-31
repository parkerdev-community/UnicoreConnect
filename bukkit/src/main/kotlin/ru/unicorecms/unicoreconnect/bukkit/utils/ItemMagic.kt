package ru.unicorecms.unicoreconnect.bukkit.utils

import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTItem
import de.tr7zw.changeme.nbtapi.utils.MinecraftVersion
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.common.types.StoreRequest
import ru.unicorecms.unicoreconnect.common.types.WarehouseItem

class ItemMagic {
    private val plugin = PluginInstance.plugin

    init {
        // Load library...
        MinecraftVersion.disableBStats()
        MinecraftVersion.disableUpdateCheck()
        MinecraftVersion.replaceLogger(plugin.logger)
        NBTItem.convertItemtoNBT(ItemStack(Material.AIR))
    }

    fun serialize(item: WarehouseItem, amount: Int = 1): ItemStack {
        val id = item.product.item_id!!.split("@")
        val damage = if (id.size == 1) 0 else id[1]

        val container = NBTContainer("{\"id\": \"${id[0]}\", \"Damage\":$damage, \"tag\":${item.product.nbt}}")
        container.setInteger("Count", amount)

        return NBTItem.convertNBTtoItem(container)
    }

    fun serialize(item: ItemStack, name: String, price: Double): StoreRequest {
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

        return StoreRequest(id, name, nbt, price)
    }
}