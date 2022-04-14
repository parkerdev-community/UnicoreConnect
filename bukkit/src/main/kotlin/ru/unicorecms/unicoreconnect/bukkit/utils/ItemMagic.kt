package ru.unicorecms.unicoreconnect.bukkit.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import ru.unicorecms.unicoreconnect.bukkit.utils.nbt.PowerNBTHelper
import ru.unicorecms.unicoreconnect.common.types.StoreRequest
import ru.unicorecms.unicoreconnect.common.types.WarehouseItem


class ItemMagic {
    private val nbtHelper = PowerNBTHelper()

    fun serialize(item: WarehouseItem, amount: Int = 1): ItemStack? {
        val id = item.product.item_id!!.split("@").toMutableList()
        val damage = if (id.size == 1) 0 else id[1].toShort()

        if (id[0].contains(":")) {
            val registryName = id[0].split(":")

            if (registryName[0] == "minecraft") {
                id[0] = when (registryName[1]) {
                    "spawn_egg" -> "MONSTER_EGG"
                    else -> registryName[1].uppercase()
                }
            }
            else id[0] = registryName.joinToString("_").uppercase()
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