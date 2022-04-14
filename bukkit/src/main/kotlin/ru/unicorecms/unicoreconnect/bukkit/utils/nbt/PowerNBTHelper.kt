package ru.unicorecms.unicoreconnect.bukkit.utils.nbt

import me.dpohvar.powernbt.nbt.NBTBase
import me.dpohvar.powernbt.nbt.NBTContainerItem
import me.limito.bukkit.jsonnbt.JsonToNBT
import me.limito.bukkit.jsonnbt.JsonToNBTLegacy
import me.limito.bukkit.jsonnbt.NBTToJson
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance

class PowerNBTHelper : NBTHelper() {
    private val plugin = PluginInstance.plugin

    private fun wrapToCraftStack(stack: ItemStack): ItemStack {
        val inv: Inventory = plugin.server.createInventory(null, 9)
        inv.addItem(stack)
        return inv.getItem(0)!!
    }

    @Throws(NBTParseException::class)
    override fun parseJson(json: String): NBTTag {
        return try {
            val nbt: NBTBase = JsonToNBT.parse(json)
            NBTTagImpl(nbt)
        } catch (_: NBTParseException) {
            val nbt: NBTBase = JsonToNBTLegacy.parse(json)
            NBTTagImpl(nbt)
        }
    }

    override fun encodeJson(nbt: NBTTag): String {
        return NBTToJson.encode((nbt as NBTTagImpl).nbt)
    }

    override fun placeTag(tag: NBTTag, stack: ItemStack): ItemStack {
        val tagItem = tag as NBTTagImpl

        val wrappedStack = wrapToCraftStack(stack)
        val container = NBTContainerItem(wrappedStack)
        container.tag = tagItem.nbt

        return wrappedStack
    }

    override fun getTag(stack: ItemStack): NBTTag? {
        return try {
            val wrappedStack = wrapToCraftStack(stack)
            val container = NBTContainerItem(wrappedStack)
            val tag = container.tag

            NBTTagImpl(tag)
        } catch (_: Exception) {
            null
        }
    }

    class NBTTagImpl(val nbt: NBTBase) : NBTTag()
}