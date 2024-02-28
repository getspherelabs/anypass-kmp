package io.spherelabs.crypto.tinypass.database.core2

import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer


fun kdbx(configuration: KdbxConfiguration): KdbxBuilder {
    return KdbxBuilder(configuration)
}


class KdbxBuilder(
    private val configuration: KdbxConfiguration,
) {
    private val fileSystem: FileSystem by lazy {
        getPlatformFileSystem()
    }

    fun write(): KdbxDatabase {
        require(configuration.path.isNotEmpty()) {
            "Path is empty."
        }

        val sink = fileSystem.sink(configuration.path.toPath()).buffer()
        return KdbxSerializer.encode(sink, configuration)

    }

    fun read(): KdbxDatabase {
        require(configuration.path.isNotEmpty()) {
            "Path is empty."
        }
        val source = fileSystem.source(configuration.path.toPath()).buffer()
        return KdbxSerializer.decode(source, write())
    }
}


data class KdbxDatabase(
    val configuration: KdbxConfiguration,
    val outerHeader: KdbxOuterHeader,
    val innerHeader: KdbxInnerHeader,
    val query: KdbxQuery,
)
