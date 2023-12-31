package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.spherelabs.local.db.AnyPassDatabase

actual class TestSqlDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            AnyPassDatabase.Schema,
            "locker_test.db",
        )
    }
}
