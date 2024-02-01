package io.spherelabs.crypto.tinypass.database.header

import okio.Source

interface Field {
    val type: Int
}

expect fun ByteArray.asKotlinxIoSource(): Source
