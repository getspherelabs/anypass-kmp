package io.spherelabs.crypto.kdbx.database.header

import okio.Source

interface Field {
    val type: Int
}

expect fun ByteArray.asKotlinxIoSource(): Source
