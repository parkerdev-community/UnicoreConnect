package ru.zirconiamc.zirconiaconnect.common

import ru.zirconiamc.zirconiaconnect.common.config.ZirconiaConfiguration

class ZirconiaInstance(config: ZirconiaConfiguration) {
    companion object {
        lateinit var server: String
    }

    init {
        server = config.server
    }
}