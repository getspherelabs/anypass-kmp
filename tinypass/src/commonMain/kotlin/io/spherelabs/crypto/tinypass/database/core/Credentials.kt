package io.spherelabs.crypto.tinypass.database.core

import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.parser.Parser
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.tinypass.database.common.decodeHexToArray
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.common.xml
import io.spherelabs.crypto.tinypass.database.model.component.EncryptedValue
import io.spherelabs.crypto.tinypass.database.model.component.SecureBytes
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private const val XmlEncoding = "utf-8"
private const val DefaultVersion = "2.0"

private val SpacesPattern = Regex("\\s+")



class Credentials private constructor(
    val passphrase: EncryptedValue?,
    val key: EncryptedValue?,
) {
    companion object {
        fun from(passphrase: EncryptedValue) = Credentials(
            passphrase = EncryptedValue.fromBinary(passphrase.binaryHash()),
            key = null,
        )

        fun from(keyData: ByteArray) = Credentials(
            passphrase = null,
            key = EncryptedValue.fromBinary(parseKeyfile(keyData)),
        )

        fun from(passphrase: EncryptedValue, keyData: ByteArray) = Credentials(
            passphrase = EncryptedValue.fromBinary(passphrase.binaryHash()),
            key = EncryptedValue.fromBinary(parseKeyfile(keyData)),
        )

        fun createKeyfile(key: ByteArray): String {
            val hash = key.sha256()
                .sliceArray(0 until 4)
                .encodeHex()
                .uppercase()

            return xml(
                version = DefaultVersion,
                XmlEncoding,
            ) {
                appendElement("")
            }.toString()
        }

        private fun parseKeyfile(keyData: ByteArray): ByteArray {
            return when (keyData.size) {
                32 -> keyData
                64 -> {
                    keyData
                        .decodeToString()
                        .lowercase()
                        .decodeHexToArray()
                }
                else -> {
                    parseXmlToKeyFile(keyData)
                        .let(::findXmlKeyData)
                        ?: keyData.sha256() // Use raw binary data as keyfile
                }
            }
        }

        private fun parseXmlToKeyFile(data: ByteArray): Document {
            return Parser.xmlParser().parseInput(data, "")
        }

        @OptIn(ExperimentalEncodingApi::class)
        private fun findXmlKeyData(element: Element): ByteArray {
            val version = element.select("meta").select("version").text().toFloat()
            val node = element.select("key").select("data")

            return when (version) {
                1.0F -> {
                    Base64.decode(node.text())
                }
                2.0f -> {
                    val hash = node.attr("hash").decodeHexToArray() ?: throw Exception("")
                    node.text()
                        .replace(SpacesPattern, "")
                        .decodeHexToArray()
                }
                else -> {
                    throw Exception("")
                }
            }
        }
    }
}

internal object KeyfileXml {
    object Tags {
        const val Document = "KeyFile"
        const val Meta = "Meta"
        const val Version = "Version"
        const val Key = "Key"
        const val Data = "Data"
    }

    object Attributes {
        const val Hash = "Hash"
    }
}
