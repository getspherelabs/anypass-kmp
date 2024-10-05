package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.model.component.SecureBytes


data class KdbxConfiguration(
    val path: String,
    val passphrase: String?,
    val key: String?,
) {
    companion object {

        fun of(path: String, passphrase: String): KdbxConfiguration {
            val text = SecureBytes.fromPlainText(passphrase).plainText

            return KdbxConfiguration(
                path = path,
                passphrase = text,
                key = null,
            )
        }

        fun of(path: String, key: ByteArray): KdbxConfiguration {
            val text = SecureBytes.from(key).plainText

            return KdbxConfiguration(
                path = path,
                passphrase = null,
                key = text,
            )
        }
    }
}
