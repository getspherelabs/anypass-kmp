package io.spherelabs.crypto.kdbx.database.core

import io.spherelabs.crypto.kdbx.database.model.component.InternalSecureBytes


data class KdbxConfiguration(
    val path: String,
    val passphrase: String?,
    val key: String?,
) {
    companion object {

        fun of(path: String, passphrase: String): KdbxConfiguration {
            val text = InternalSecureBytes.fromPlainText(passphrase).plainText

            return KdbxConfiguration(
                path = path,
                passphrase = text,
                key = null,
            )
        }

        fun of(path: String, key: ByteArray): KdbxConfiguration {
            val text = InternalSecureBytes.from(key).plainText

            return KdbxConfiguration(
                path = path,
                passphrase = null,
                key = text,
            )
        }
    }
}
