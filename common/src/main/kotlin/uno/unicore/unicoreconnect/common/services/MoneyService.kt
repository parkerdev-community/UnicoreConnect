package uno.unicore.unicoreconnect.common.services

import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.types.Money
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
}