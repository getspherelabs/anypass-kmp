package io.spherelabs.crypto.tinypass.database.xml

import io.spherelabs.crypto.tinypass.database.header.Version
import io.spherelabs.crypto.tinypass.database.model.component.CustomDataValue
import okio.ByteString

data class XmlOption(
    val version: Version,
    val isExportable: Boolean = false,
    val binaries: Map<ByteString, CustomDataValue>,
)
