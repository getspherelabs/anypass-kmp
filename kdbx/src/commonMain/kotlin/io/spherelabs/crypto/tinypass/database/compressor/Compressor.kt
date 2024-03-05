package io.spherelabs.crypto.tinypass.database.compressor


expect fun ByteArray.ungzip(): ByteArray
expect fun ByteArray.gzip(): ByteArray
