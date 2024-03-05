package io.spherelabs.crypto.tinypass.database.header

import java.io.ByteArrayInputStream
import okio.Source
import okio.source

actual fun ByteArray.asKotlinxIoSource(): Source {
    return ByteArrayInputStream(this).source()
}
