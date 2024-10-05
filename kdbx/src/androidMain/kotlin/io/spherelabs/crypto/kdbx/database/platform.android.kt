package io.spherelabs.crypto.kdbx.database

import okio.FileSystem

actual fun getPlatformFileSystem(): FileSystem {
    return FileSystem.SYSTEM
}
