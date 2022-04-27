package ru.unicorecms.unicoreconnect.bukkit.utils.nbt

import org.bukkit.inventory.ItemStack

abstract class NBTHelper {
    @Throws(NBTParseException::class)
    abstract fun parseJson(json: String): NBTTag

    abstract fun encodeJson(nbt: NBTTag): String

    abstract fun placeTag(tag: NBTTag, stack: ItemStack): ItemStack

    abstract fun getTag(stack: ItemStack): NBTTag?
}