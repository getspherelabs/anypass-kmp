package io.spherelabs.crypto.tinypass.database

import okio.FileSystem

actual fun getPlatformFileSystem() = FileSystem.SYSTEM
