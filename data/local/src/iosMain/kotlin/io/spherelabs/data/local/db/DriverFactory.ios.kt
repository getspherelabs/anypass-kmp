package io.spherelabs.data.local.db

import io.spherelabs.local.db.LockerDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {

        return NativeSqliteDriver(LockerDatabase.Schema, "locker.db").also {
            it.execute(null,"PRAGMA foreign_keys=ON",0)
        }
    }
}