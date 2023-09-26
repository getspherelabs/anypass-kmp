package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.spherelabs.local.db.AnyPassDatabase

actual class TestSqlDriverFactory {
    actual fun createDriver(): SqlDriver {

        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also(AnyPassDatabase.Schema::create)
    }
}
