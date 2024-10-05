package io.spherelabs.crypto.tinypass.database.header

import okio.Buffer
import okio.Source

actual fun ByteArray.asKotlinxIoSource(): Source {
    return Buffer().buffer
}
