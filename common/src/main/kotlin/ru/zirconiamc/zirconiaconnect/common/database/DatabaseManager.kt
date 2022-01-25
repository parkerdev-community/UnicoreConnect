package ru.zirconiamc.zirconiaconnect.common.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseManager {
    fun connection() {
        Database.connect("jdbc:mysql://localhost:3306/dev", driver = "com.mysql.jdbc.Driver", user = "root", password = "12345678");
    }
}