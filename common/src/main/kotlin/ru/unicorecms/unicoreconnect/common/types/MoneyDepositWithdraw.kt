package ru.unicorecms.unicoreconnect.common.types

import ru.unicorecms.unicoreconnect.common.UnicoreCommon

class MoneyDepositWithdraw (
    val user_uuid: String,
    val amount: Double,
) {
    val server_id: String = UnicoreCommon.config.server
}