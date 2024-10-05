package io.spherelabs.crypto.kdbx.database.header

import java.io.ByteArrayInputStream
import okio.Source
import okio.source

actual fun ByteArray.asKotlinxIoSource(): Source {
    return ByteArrayInputStream(this).source()
}
