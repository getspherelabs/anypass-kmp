package io.spherelabs.crypto.tinypass.database.xml

import io.spherelabs.crypto.tinypass.database.header.KdbxVersion
import io.spherelabs.crypto.tinypass.database.model.component.CustomDataValue
import okio.ByteString

data class XmlOption(
    val kdbxVersion: KdbxVersion,
    val isExportable: Boolean = false,
    val binaries: Map<ByteString, CustomDataValue>,
)
