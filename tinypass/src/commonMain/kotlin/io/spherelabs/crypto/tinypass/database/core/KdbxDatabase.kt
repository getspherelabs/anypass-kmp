package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.buffer.KdbxReader
import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import kotlin.coroutines.CoroutineContext
import okio.Buffer
import okio.Path.Companion.toPath

data class KdbxDatabase(
    val configuration: KdbxConfiguration,
    val outerHeader: KdbxOuterHeader,
    val innerHeader: KdbxInnerHeader,
    val query: KdbxQuery,
) {

    companion object {
        fun isEmpty(path: String): Boolean {
            return getPlatformFileSystem().exists(path.toPath())
        }

        fun write(configuration: KdbxConfiguration): KdbxDatabase {
            return if (!isEmpty(configuration.path)) KdbxSerializer.encode(configuration) else {
                throw Exception("File is existed!")
            }
        }
    }

    fun read(): KdbxQuery {
        TODO()
    }

}
