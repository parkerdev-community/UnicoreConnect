package ru.zirconiamc.zirconiaconnect.common.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class Initializer(
    hikariConfig: HikariConfig
) {
    private val dataSource: HikariDataSource

    init {
        dataSource = HikariDataSource(hikariConfig)
    }

    fun connect() = Database.connect(dataSource)
}