package ru.unicorecms.unicoreconnect.common.types

import com.google.gson.annotations.SerializedName

enum class GiveMethod {
    @SerializedName("0")
    UnicoreConnect,
    @SerializedName("1")
    UnicoreConnectCommand,
    @SerializedName("2")
    RCON,
}

class Product {
    var name: String? = null
    var commands: List<String>? = null
    val give_method: GiveMethod = GiveMethod.UnicoreConnect
    var nbt: String? = null
    var item_id: String? = null
}