package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer

data class KdbxDatabase(
    val configuration: KdbxConfiguration,
    val outerHeader: KdbxOuterHeader,
    val innerHeader: KdbxInnerHeader,
    val query: KdbxQuery,
) {
//    private val decoder = KdbxSerializer.decode(configuration)

    fun read(): KdbxQuery {
        TODO()
    }

    fun write() {
        KdbxSerializer.encode(configuration)
    }
}
