package ru.unicorecms.unicoreconnect.bukkit.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import ru.unicorecms.unicoreconnect.bukkit.utils.nbt.PowerNBTHelper
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.StoreRequest
import ru.unicorecms.unicoreconnect.common.types.WarehouseItem

class ItemMagic {
    private val nbtHelper = PowerNBTHelper()

    fun serialize(item: WarehouseItem, amount: Int = 1): ItemStack? {
        val id = item.product.item_id!!.split("@").toMutableList()
        val damage = if (id.size == 1) 0 else id[1].toShort()

        if (id[0].contains(":")) {
            val registryName = id[0].split(":")
            var tempId = if (registryName[0] == "minecraft") registryName[1].uppercase()
            else registryName.joinToString("_").uppercase()

            for (its in UnicoreCommon.itemsMap.entries) {
                if (its.key.replaceFirst("_", ":") ==  id[0]) tempId = its.value
            }

            id[0] = tempId
        }

        val material = Material.getMaterial(id[0]) ?: return null
        val itemStack = ItemStack(material, amount, damage)

        if (item.product.nbt == null) return itemStack

        return nbtHelper.placeTag(nbtHelper.parseJson(item.product.nbt!!), itemStack)
    }

    fun serialize(item: ItemStack, name: String, price: Double): StoreRequest {
        var id = item.type.name
        val damage = item.durability.toInt()
        val tag = nbtHelper.getTag(item)?.let { nbtHelper.encodeJson(it) }

        if (id == Material.AIR.name)
            throw Exception("AIR not supported!")

        if (damage != 0)
            id = "$id@$damage"

        return StoreRequest(id, name, tag, price)
    }
}