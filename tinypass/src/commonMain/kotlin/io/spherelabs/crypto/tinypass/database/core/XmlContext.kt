package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.header.KdbxVersion
import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import okio.ByteString

sealed class XmlContext {
    abstract val kdbxVersion: KdbxVersion

    class Encode(
        override val kdbxVersion: KdbxVersion,
        val encryption: EncryptionSaltGenerator,
        val binaries: Map<ByteString, BinaryData>,
        val isXmlExport: Boolean = false
    ) : XmlContext()

    class Decode(
        override val kdbxVersion: KdbxVersion,
        val encryption: EncryptionSaltGenerator,
        val binaries: Map<ByteString, BinaryData>,
        val untitledLabel: String = ""
    ) : XmlContext()
}
