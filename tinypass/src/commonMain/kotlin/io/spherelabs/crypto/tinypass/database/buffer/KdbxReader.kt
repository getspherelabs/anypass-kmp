package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader


interface KdbxReader {
    fun readOuterHeader(): KdbxOuterHeader
    fun readInnerHeader(): KdbxInnerHeader
}
