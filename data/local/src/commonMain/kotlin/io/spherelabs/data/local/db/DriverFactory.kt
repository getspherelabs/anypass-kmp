package io.spherelabs.data.local.db

import app.cash.sqldelight.db.SqlDriver
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: DriverFactory): AnyPassDatabase {
    return AnyPassDatabase.invoke(driver.createDriver(), OtpEntity.Adapter(
        digitAdapter = OtpDigitColumnAdapter(),
        durationAdapter = OtpDurationColumnAdapter(),
        typeAdapter = OtpTypeColumnAdapter()
    ))
}
