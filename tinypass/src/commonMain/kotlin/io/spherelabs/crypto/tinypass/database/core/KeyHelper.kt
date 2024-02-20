@file:OptIn(ExperimentalEncodingApi::class)

package io.spherelabs.crypto.tinypass.database.core

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.tinypass.database.common.decodeHexToArray
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.common.xml
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import io.spherelabs.crypto.tinypass.database.model.component.SecureBytes
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data class Credentials(
    val passphrase: String?,
    val key: String?,
) {
    companion object {
        fun from(passphrase: String): Credentials {
            return Credentials(
                passphrase = SecureBytes.fromPlainText(passphrase).plainText,
                key = null
            )
        }
    }
}

class KeyHelper private constructor(
    val passphrase: SecureBytes?,
    val key: SecureBytes?,
) {
    companion object {
        fun from(passphrase: SecureBytes): KeyHelper {
            return KeyHelper(
                passphrase = SecureBytes.from(passphrase.sha256),
                key = null,
            )
        }

        fun from(key: ByteArray): KeyHelper {
            return KeyHelper(
                passphrase = null,
                key = SecureBytes.from(parseKeyfile(key)),
            )
        }

        fun createKeyfile(key: ByteArray): String {
            val hash = key.sha256()
                .sliceArray(0 until 4)
                .encodeHex()
                .uppercase()

            return buildKeyfileXml(hash, key)
        }

        private fun buildKeyfileXml(hash: String, key: ByteArray): String {
            return xml(
                version = DEFAULT_VERSION,
                charset = CHARSET,
            ) {
                appendElement(META).apply {
                    appendElement(VERSION).text(DEFAULT_VERSION)
                }
                appendElement(KEY).apply {
                    appendElement(DATA).apply {
                        attr(HASH, hash)
                        text(key.encodeHex().uppercase())
                    }
                }
            }.toString()
        }

        private fun parseKeyfile(bytes: ByteArray): ByteArray {
            return when (bytes.size) {
                32 -> bytes
                64 -> {
                    bytes
                        .decodeToString()
                        .lowercase()
                        .decodeHexToArray()
                }
                else -> {
                    deserialize(bytes)
                }
            }
        }

        private fun deserialize(bytes: ByteArray): ByteArray {
            return xmlParser(bytes).let(::deserialize)
        }

        private fun deserialize(element: Element): ByteArray {
            val version = element.select(META).select(VERSION).text().toFloat()
            val node = element.select(KEY).select(DATA)

            return when (version) {
                1.0F -> {
                    Base64.decode(node.text())
                }
                2.0f -> {
                    val hash = node.attr(HASH).decodeHexToArray()
                    node.text()
                        .replace(PATTERN, "")
                        .decodeHexToArray()
                }
                else -> {
                    throw Exception("")
                }
            }
        }

        private const val CHARSET = "utf-8"
        private val PATTERN = Regex("\\s+")

        private const val DEFAULT_VERSION = "2.0"

        private const val META = "meta"
        private const val VERSION = "version"
        private const val KEY = "key"
        private const val HASH = "hash"
        private const val DATA = "data"
    }
}
