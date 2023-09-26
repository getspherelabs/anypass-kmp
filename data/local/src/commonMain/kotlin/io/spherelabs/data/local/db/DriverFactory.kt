package io.spherelabs.data.local.db

import app.cash.sqldelight.db.SqlDriver
import io.spherelabs.local.db.AnyPassDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: DriverFactory): AnyPassDatabase {
    return AnyPassDatabase.invoke(driver.createDriver())
}
