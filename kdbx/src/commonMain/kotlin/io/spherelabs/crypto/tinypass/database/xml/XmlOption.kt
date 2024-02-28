package io.spherelabs.crypto.tinypass.database.xml

import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.header.KdbxVersion
import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import io.spherelabs.crypto.tinypass.database.model.component.CustomDataValue
import okio.ByteString

data class XmlOption(
    val kdbxVersion: KdbxVersion,
    val salt: EncryptionSaltGenerator,
    val isExportable: Boolean = false,
    val binaries: Map<ByteString, BinaryData>,
)
