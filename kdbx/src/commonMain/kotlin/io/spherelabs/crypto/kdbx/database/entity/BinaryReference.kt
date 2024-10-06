package io.spherelabs.crypto.kdbx.database.entity

import okio.ByteString

data class BinaryReference(
    val hash: ByteString,
    val name: String,
)
