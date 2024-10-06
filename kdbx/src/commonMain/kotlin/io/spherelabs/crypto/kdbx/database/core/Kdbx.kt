package io.spherelabs.crypto.kdbx.database.core

import io.spherelabs.crypto.kdbx.database.getPlatformFileSystem
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery
import io.spherelabs.crypto.kdbx.database.serializer.KdbxSerializer
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer

/**
 * A [Kdbx] database comprises two main parts: a meta data section and the actual data section.
 *
 * The meta data section contains general information about the database itself,
 * maintenance information (e.g., when and how to clean up deleted entries),
 * access and modification information (e.g., date and time of the last database title change),
 * and additional global resources (e.g., custom icons).
 *
 * The data section contains the actual user data as a tree structure of groups and entries.
 * A group is an element with various attributes containing zero or more entries. A group can also contain zero or more sub groups, which can again contain groups and entries.
 * The format of a group is described in (#groups).An entry is a set of user credentials and other related data.
 * An entry MUST have a single parent group. The attributes of an entry are described in (#entries).
 *
 * A database MUST have exactly one root group, which acts as a common ancestor of all other groups and entries.
 * The KDBX4 format has two binary file headers: the outer and the inner header.
 * The former is unencrypted and contains all the information necessary to decrypt the file’s payload.
 * The latter is stored in the encrypted part of the file.
 *
 *
 *   +------------+      +------------+
 *   | KdbxReader |----->| KdbxWriter |
 *   +------------+      +------------+
 *          \                /
 *           \              /
 *            v            v
 *           +--------------+
 *           |  KdbxBuffer   |
 *           +--------------+
 *
 *                 |
 *                 v
 *          +---------------+
 *          | KdbxSerializer |
 *          +---------------+
 *            /         \
 *           v           v
 *    +------------+  +------------+
 *    | XmlReader  |  | XmlWriter  |
 *    +------------+  +------------+
 *
 *                 |
 *                 v
 *           +-------------+
 *           | KdbxBuilder |
 *           +-------------+
 *
 */

interface Kdbx {
    suspend fun write()
    suspend fun write(query: KdbxQuery?)
    suspend fun open()
    suspend fun read(): KdbxDatabase
    fun isEmpty(): Boolean
    suspend fun read(masterPassword: String): KdbxDatabase
}

@KdbxDsl
class KdbxBuilder {

    var path: String = ""
    var passphrase: String? = null
    var key: String? = null

    @kotlin.concurrent.Volatile
    private var database: KdbxDatabase? = null

    private val fileSystem: FileSystem by lazy {
        getPlatformFileSystem()
    }

    private val mutex = Mutex()


    // Builds the Kdbx object
    fun build(): Kdbx {
        require(path.isNotEmpty()) { "Path cannot be empty." }

        val configuration = KdbxConfiguration.of(path, checkNotNull(passphrase) {
            "Passphrase cannot be empty."
        })

        return object : Kdbx {

            override fun isEmpty(): Boolean {
               return !fileSystem.exists(path.toPath())
            }

            override suspend fun open() {
                if (!fileSystem.exists(path.toPath())) {
                    throw IllegalArgumentException("File does not exist.")
                }
                withContext(Dispatchers.IO) {
                    mutex.withLock {
                        val sink = fileSystem.sink(path.toPath()).buffer()
                        database = KdbxSerializer.encode(sink, configuration)
                    }
                }
            }

            override suspend fun write(query: KdbxQuery?) {
                if (!fileSystem.exists(path.toPath())) {
                    throw IllegalArgumentException("File does not exist.")
                }
                withContext(Dispatchers.IO) {
                    mutex.withLock {
                        val sink = fileSystem.sink(path.toPath()).buffer()
                        if (query != null) {
                            database = KdbxSerializer.encode(sink, configuration, query)
                        }

                    }
                }
            }

            override suspend fun write() {
                if (!fileSystem.exists(path.toPath())) {
                    val sink = fileSystem.sink(path.toPath(), mustCreate = true).buffer()
                    database = KdbxSerializer.encode(sink, configuration)
                } else {
                    open()
                }
            }

            override suspend fun read(): KdbxDatabase {
                val source = fileSystem.source(path.toPath()).buffer()
                val db = checkNotNull(database) {
                    "Database is null."
                }
                return withContext(Dispatchers.IO) {
                    mutex.withLock {
                        KdbxSerializer.decode(source, db)
                    }
                }
            }

            override suspend fun read(masterPassword: String): KdbxDatabase {
                require(passphrase == masterPassword) {
                    "Master password is incorrect."
                }
                val source = fileSystem.source(path.toPath()).buffer()
                val db = checkNotNull(database) {
                    "Database is null."
                }
                return withContext(Dispatchers.IO) {
                    mutex.withLock {
                        KdbxSerializer.decode(source, db)
                    }
                }
            }
        }
    }
}

@DslMarker
annotation class KdbxDsl

@KdbxDsl
fun kdbx(block: KdbxBuilder.() -> Unit): Kdbx {
    return KdbxBuilder().apply(block).build()
}
