package io.spherelabs.crypto.tinypass.database.entity

import okio.ByteString

data class BinaryReference(
    val hash: ByteString,
    val name: String,
)
