package ru.unicorecms.unicoreconnect.bukkit.hooks.vault

import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.OfflinePlayer
import ru.unicorecms.unicoreconnect.bukkit.PluginInstance
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import java.text.DecimalFormat

class VaultEconomyHook : EconomyWrapper() {
    private val plugin = PluginInstance.plugin
    private var currencyFormatter = DecimalFormat("0.00")

    companion object {}

    override fun isEnabled(): Boolean {
        return plugin.isEnabled
    }

    override fun getName(): String {
        return plugin.name
    }


    override fun fractionalDigits(): Int {
        return 2
    }

    override fun format(amount: Double): String {
        return currencyFormatter.format(amount)
    }

    override fun currencyNamePlural(): String {
        return "$"
    }

    override fun currencyNameSingular(): String {
        return "$"
    }

    override fun hasAccount(player: OfflinePlayer): Boolean {
        return UnicoreCommon.moneyService.checkOne(player.uniqueId)
    }

    override fun getBalance(player: OfflinePlayer): Double {
        return UnicoreCommon.moneyService.findOne(player.uniqueId).money
    }

    override fun has(player: OfflinePlayer, amount: Double): Boolean {
        return true
    }

    override fun withdrawPlayer(player: OfflinePlayer, initialAmount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun depositPlayer(player: OfflinePlayer, initialAmount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun createPlayerAccount(player: OfflinePlayer): Boolean {
        return UnicoreCommon.moneyService.checkOne(player.uniqueId)
    }
}