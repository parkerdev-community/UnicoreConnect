package uno.unicore.unicoreconnect.common.types

class BanRequest(
    var user_uuid: String,
    var reason: String,
    var actor_uuid: String? = null,
    var expires: Long? = null
) {}