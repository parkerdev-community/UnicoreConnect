package ru.unicorecms.unicoreconnect.common.types

import ru.unicorecms.unicoreconnect.common.UnicoreCommon

class MoneyTransfer (
    val user_uuid: String,
    val user_ip: String,
    val target_uuid: String,
    val amount: Double,
) {
    val server = UnicoreCommon.config.server
}