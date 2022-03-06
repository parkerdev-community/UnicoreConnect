package uno.unicore.unicoreconnect.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import uno.unicore.unicoreconnect.common.config.UnicoreConfig
import uno.unicore.unicoreconnect.common.http.UnicoreRequester
import uno.unicore.unicoreconnect.common.services.BanService
import uno.unicore.unicoreconnect.common.services.MoneyService
import uno.unicore.unicoreconnect.common.services.PlaytimeService
import uno.unicore.unicoreconnect.common.services.ServerService

class UnicoreCommon(pluginConfig: UnicoreConfig) {
    companion object {
        val gson: Gson = GsonBuilder().create()

        lateinit var config: UnicoreConfig
        lateinit var requester: UnicoreRequester
        lateinit var socketClient: SocketClient

        // Services
        lateinit var serversService: ServerService
        lateinit var banService: BanService
        lateinit var moneyService: MoneyService
        lateinit var playtimeService: PlaytimeService
    }

    init {
        config = pluginConfig
        requester = UnicoreRequester()
        socketClient = SocketClient()

        serversService = ServerService()
        banService = BanService()
        moneyService = MoneyService()
        playtimeService = PlaytimeService()
    }
}
