package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature


interface KdbxReader {
    fun readOuterHeader(): KdbxOuterHeader
    fun readInnerHeader(): KdbxInnerHeader
    fun readSignature(): KdbxSignature
}
