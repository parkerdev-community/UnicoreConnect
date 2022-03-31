package ru.unicorecms.unicoreconnect.common.services

import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.Money
import ru.unicorecms.unicoreconnect.common.types.MoneyDepositWithdraw
import ru.unicorecms.unicoreconnect.common.types.MoneyTransfer
import java.util.*

class MoneyService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/cabinet/money"

    fun checkOne(uuid: UUID): Boolean {
        return UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").response.isSuccessful
    }

    fun findOne(uuid: UUID): Money {
        return UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow()
    }

    fun transfer(uuid: UUID, ip: String, target_uuid: UUID, amount: Double): Money {
        return UnicoreCommon.requester.post("$baseUrl/user", MoneyTransfer(
            uuid.toString(),
            ip,
            target_uuid.toString(),
            amount
        )).getOrThrow()
    }

    fun deposit(uuid: UUID, amount: Double): Money {
        return UnicoreCommon.requester.post("$baseUrl/user/deposit", MoneyDepositWithdraw(uuid.toString(), amount)).getOrThrow()
    }

    fun withdraw(uuid: UUID, amount: Double): Money {
        return UnicoreCommon.requester.post("$baseUrl/user/withdraw", MoneyDepositWithdraw(uuid.toString(), amount)).getOrThrow()
    }

    fun top(): Array<Money> {
        return UnicoreCommon.requester.get("$baseUrl/top/${config.server}").getOrThrow()
    }
}