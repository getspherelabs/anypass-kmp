package io.spherelabs.crypto.tinypass.database.core

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.nodes.XmlDeclaration
import com.fleeksoft.ksoup.ported.BufferReader
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.model.component.EncryptedValue
import okio.Buffer
import okio.buffer

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

            return createXmlDocument(
                version = DefaultVersion,
                XmlEncoding,
            ).toString()
        }

//        private fun parseKeyfile(keyData: ByteArray): ByteArray {
//            return when (keyData.size) {
//                32 -> keyData
//                64 -> {
//                    keyData
//                        .toString(Charsets.UTF_8)
//                        .lowercase()
//                        .decodeHexToArray()
//                }
//                else -> {
//                    parseXmlKeyfile(keyData)
//                        ?.let(::findXmlKeyData)
//                        ?: keyData.sha256() // Use raw binary data as keyfile
//                }
//            }
//        }

        private fun parseXmlToKeyFile(data: ByteArray): Document? {
            return Ksoup.parse(
                BufferReader(data),
                baseUri = "",
                charsetName = null,
            )
        }
//        private fun parseXmlKeyfile(keyData: ByteArray): Node? = try {
//            parse(ByteArrayInputStream(keyData))
//        } catch (e: Exception) {
//            null
//        }

//
//        private fun findXmlKeyData(node: Node): ByteArray {
//            val version = node
//                .firstOrNull(KeyfileXml.Tags.Meta)
//                ?.firstOrNull(KeyfileXml.Tags.Version)
//                ?.getText()
//                ?.toFloatOrNull()
//                ?: throw KeyfileError.InvalidVersion()
//            val dataNode = node
//                .firstOrNull(KeyfileXml.Tags.Key)
//                ?.firstOrNull(KeyfileXml.Tags.Data)
//                ?: throw KeyfileError.NoKeyData()
//
//            return when (version) {
//                1.0f -> {
//                    dataNode.getText()
//                        ?.decodeBase64ToArray()
//                        ?: throw KeyfileError.NoKeyData()
//                }
//                2.0f -> {
//                    val hash = dataNode.get<String?>(KeyfileXml.Attributes.Hash)
//                        ?.decodeHexToArray()
//                        ?: throw KeyfileError.InvalidHash()
//                    dataNode.getText()
//                        ?.replace(SpacesPattern, "")
//                        ?.decodeHexToArray()
//                        ?.also { data ->
//                            if (!data.sha256().sliceArray(0 until 4).contentEquals(hash)) {
//                                throw KeyfileError.InvalidHash()
//                            }
//                        }
//                        ?: throw KeyfileError.NoKeyData()
//                }
//                else -> throw KeyfileError.InvalidVersion()
//            }
//        }
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

private fun createXmlDocument(
    version: String,
    charset: String,
): Document {
    val doc = Document("")
    doc.appendElement("root").text("keyfile")
    doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
    val xmlDeclaration = XmlDeclaration("xml", false)
    xmlDeclaration.attr("version", version)
    xmlDeclaration.attr("encoding", charset)
    doc.prependChild(xmlDeclaration)

    return doc
}
