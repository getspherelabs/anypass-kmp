package io.spherelabs.crypto.kdbx.database.xml

import io.spherelabs.crypto.kdbx.database.EncryptionSaltGenerator
import io.spherelabs.crypto.kdbx.database.header.KdbxVersion
import io.spherelabs.crypto.kdbx.database.model.component.BinaryData
import okio.ByteString

data class XmlOption(
    val kdbxVersion: KdbxVersion,
    val salt: EncryptionSaltGenerator,
    val isExportable: Boolean = false,
    val binaries: Map<ByteString, BinaryData>,
)
