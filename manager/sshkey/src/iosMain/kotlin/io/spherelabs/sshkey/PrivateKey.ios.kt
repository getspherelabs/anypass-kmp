package io.spherelabs.sshkey

import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Security.*

actual class PrivateKey {
    actual val encoded: ByteArray = byteArrayOf()
}
