package ru.unicorecms.unicoreconnect.common.types

import ru.unicorecms.unicoreconnect.common.UnicoreCommon

class StoreRequest(
    val id: String,
    val name: String,
    val nbt: String?,
    val price: Double,
) {
    val server = UnicoreCommon.server?.id
}