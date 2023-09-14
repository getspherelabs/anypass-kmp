package io.spherelabs.data.local.db

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.spherelabs.local.db.LockerDatabase
import io.spherelabs.lockerkmp.local.MR
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        val database: File = context.getDatabasePath("locker.db")

        if (!database.exists()) {
            val inputStream = context.resources.openRawResource(MR.files.locker.rawResId)
            val outputStream = FileOutputStream(database.absolutePath)

            inputStream.use { input: InputStream ->
                outputStream.use { output: FileOutputStream ->
                    input.copyTo(output)
                }
            }

        }

        return AndroidSqliteDriver(
            schema = LockerDatabase.Schema,
            context = context,
            name = "locker.db",
            callback = object : AndroidSqliteDriver.Callback(LockerDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}
