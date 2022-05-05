package ru.unicorecms.unicoreconnect.common.services

import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.StoreRequest
import ru.unicorecms.unicoreconnect.common.types.WarehouseItem
import java.util.UUID

class ShowcaseService {
    private var config = UnicoreCommon.config

    fun create(payload: StoreRequest): Boolean {
        return UnicoreCommon.requester.post("${config.apiUrl}/store/products/from_game", payload).response.isSuccessful
    }

    fun find(uuid: UUID): Array<WarehouseItem> {
        return UnicoreCommon.requester.get("${config.apiUrl}/store/warehouse/$uuid/${config.server}").getOrThrow()
    }

    fun gived(items: ArrayList<WarehouseItem>): ArrayList<WarehouseItem> {
        if (items.size == 0) return items
        val glow = arrayListOf<WarehouseItem>()

        for (item in items) {
            val index = glow.indexOf(item)
            if (index == -1) {
                item.amount = 1
                glow.add(item)
            } else {
                glow[index].amount += 1
            }
        }

        return UnicoreCommon.requester.post("${config.apiUrl}/store/warehouse", glow).getOrThrow()
    }
}