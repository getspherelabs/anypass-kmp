package io.spherelabs.data.local.db

import app.cash.sqldelight.db.SqlDriver
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: DriverFactory): AnyPassDatabase {
    return AnyPassDatabase.invoke(driver.createDriver(), )
}
