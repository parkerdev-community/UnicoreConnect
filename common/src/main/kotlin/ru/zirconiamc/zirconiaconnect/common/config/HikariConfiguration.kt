package ru.zirconiamc.zirconiaconnect.common.config

import com.zaxxer.hikari.HikariConfig

class HikariConfiguration(
    url: String,
    user: String?,
    password: String?
) {
    private val config = HikariConfig()

    init {
        config.jdbcUrl = url
        config.username = user
        config.password = password
    }

    fun get(): HikariConfig = config
}