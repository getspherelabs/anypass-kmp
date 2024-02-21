package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxVersion
import io.spherelabs.crypto.tinypass.database.header.OuterHeaderOption
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature


interface KdbxWriter {
    fun writeOuterHeader(outerHeader: KdbxOuterHeader)
    fun writeInnerHeader(innerHeader: KdbxInnerHeader)
    fun writeSignature(signature: KdbxSignature)
    fun writeVersion(kdbxVersion: KdbxVersion)
    fun writeOuterHeaderOption(option: OuterHeaderOption)
}
