package io.spherelabs.crypto.kdbx.database.compressor


expect fun ByteArray.ungzip(): ByteArray
expect fun ByteArray.gzip(): ByteArray
