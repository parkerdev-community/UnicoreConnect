package uno.unicore.unicoreconnect.common.services

import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.types.StoreRequest

class ShowcaseService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/store/products"

    fun create(payload: StoreRequest): Boolean {
        return UnicoreCommon.requester.post("$baseUrl/from_game", payload).response.isSuccessful
    }
}