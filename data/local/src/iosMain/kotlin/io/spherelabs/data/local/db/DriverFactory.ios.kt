package io.spherelabs.data.local.db

import io.spherelabs.local.db.LockerDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.lockerkmp.local.MR
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSUserDomainMask
import platform.Foundation.stringByAppendingPathComponent

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val fileManager = NSFileManager.defaultManager
        val documentsPath = NSSearchPathForDirectoriesInDomains(
            directory = NSApplicationSupportDirectory,
            domainMask = NSUserDomainMask,
            expandTilde = true
        ).first() as NSString

        val dbDirectoryPath = documentsPath.stringByAppendingPathComponent("databases")
        val targetDbPath = documentsPath.stringByAppendingPathComponent("databases/locker.db")


        val directoryExists = fileManager.fileExistsAtPath(dbDirectoryPath)
        val databaseExists = fileManager.fileExistsAtPath(targetDbPath)
        val sourceDbPath = MR.files.locker.path


        if (databaseExists.not()) {
            memScoped {
                // you can use it to log errors
                val error: ObjCObjectVar<NSError?> = alloc()

                if (directoryExists.not()) {
                    val createSuccess = fileManager.createDirectoryAtPath(
                        path = dbDirectoryPath,
                        withIntermediateDirectories = true,
                        attributes = null,
                        error = error.ptr
                    )
                }

                val copySuccess = fileManager.copyItemAtPath(
                    srcPath = sourceDbPath,
                    toPath = targetDbPath,
                    error = error.ptr
                )
            }
        }


        return NativeSqliteDriver(AnyPassDatabase.Schema, "locker.db").also {
            it.execute(null,"PRAGMA foreign_keys=ON",0)
        }
    }
}
