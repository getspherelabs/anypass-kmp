package io.spherelabs.crypto.tinypass.database.model.component

import okio.ByteString

data class BinaryReference(
    val hash: ByteString,
    val name: String,
)
