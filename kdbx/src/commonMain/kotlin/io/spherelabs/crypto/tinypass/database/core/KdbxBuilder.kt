package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import kotlin.jvm.Volatile
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer


fun kdbx(configuration: KdbxConfiguration): KdbxBuilder {
    return KdbxBuilder(configuration)
}

class KdbxBuilder(
    private val configuration: KdbxConfiguration,
) {
    @Volatile
    private var database: KdbxDatabase? = null

    private val fileSystem: FileSystem by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getPlatformFileSystem()
    }

    private val mutex = Mutex()

    suspend fun write() {
        require(configuration.path.isNotEmpty()) {
            "Path is empty."
        }

        mutex.withLock {
            if (!fileSystem.exists(configuration.path.toPath())) {
                val sink = fileSystem.sink(configuration.path.toPath(), mustCreate = true).buffer()
                database = KdbxSerializer.encode(sink, configuration)
            } else {
                open()
            }
        }
    }

    suspend fun open() {
        require(configuration.path.isNotEmpty()) {
            "Path is empty."
        }

        require(fileSystem.exists(configuration.path.toPath())) {
            "File does not existed."
        }

        mutex.withLock {
            val sink = fileSystem.sink(configuration.path.toPath()).buffer()
            database = KdbxSerializer.encode(sink, configuration)
        }
    }

    suspend fun read(): KdbxDatabase {
        require(configuration.path.isNotEmpty()) {
            "Path is empty."
        }

        return withContext(context = Dispatchers.IO) {
            mutex.withLock {
                val source = fileSystem.source(configuration.path.toPath()).buffer()
                val db = checkNotNull(database) {
                    "Database is null."
                }
                KdbxSerializer.decode(source, db)
            }
        }
    }

    suspend fun read(masterPassword: String): KdbxDatabase {
        require(masterPassword.isNotEmpty()) {
            "Master password is empty."
        }

        require(configuration.passphrase == masterPassword) {
            "Master password is incorrect."
        }

        return withContext(Dispatchers.IO) {
            mutex.withLock {
                val source = fileSystem.source(configuration.path.toPath()).buffer()

                val db = checkNotNull(database) {
                    "Database is null."
                }

                KdbxSerializer.decode(source, db)
            }
        }
    }
}



