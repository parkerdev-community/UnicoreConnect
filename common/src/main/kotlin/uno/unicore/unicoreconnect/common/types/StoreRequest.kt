package uno.unicore.unicoreconnect.common.types

import uno.unicore.unicoreconnect.common.UnicoreCommon

class StoreRequest(
    val id: String,
    val nbt: String?,
    val price: Double,
) {
    val server = UnicoreCommon.server?.id
}