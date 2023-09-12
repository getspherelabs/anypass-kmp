package io.spherelabs.data.local.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.spherelabs.local.db.LockerDatabase

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = LockerDatabase.Schema,
            context = context,
            name = "locker.db"
        )
    }
}
