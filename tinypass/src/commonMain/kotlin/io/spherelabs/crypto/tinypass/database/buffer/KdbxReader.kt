package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import okio.BufferedSource


interface KdbxReader {
    fun readOuterHeader(): KdbxOuterHeader
    fun readInnerHeader(): KdbxInnerHeader
    fun readInnerHeader(bufferedSource: BufferedSource): KdbxInnerHeader
    fun readOuterHeader(bufferedSource: BufferedSource): KdbxOuterHeader
}
