@file:OptIn(ExperimentalEncodingApi::class)

package io.spherelabs.crypto.kdbx.database.model.component

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.kdbx.database.compressor.gzip
import io.spherelabs.crypto.kdbx.database.compressor.ungzip
import io.spherelabs.crypto.kdbx.database.model.autotype.toXmlString
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import okio.*
import okio.ByteString.Companion.toByteString

sealed class BinaryData(
    val hash: ByteString,
) {
    abstract val memoryProtection: Boolean
    abstract val rawContent: ByteArray

    abstract fun serialize(id: Int): Element
    abstract fun getContent(): ByteArray

    data class Uncompressed(
        override val memoryProtection: Boolean,
        override val rawContent: ByteArray,
    ) : BinaryData(
        rawContent.toByteString().sha256(),
    ) {

        override fun getContent() = rawContent.ungzip()

        fun toCompressed(): Compressed {
            try {
                val bytes = rawContent.gzip()
                return Compressed(
                    rawContent = bytes,
                    memoryProtection = memoryProtection,
                )
            } catch (exception: IOException) {
                throw exception
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as Uncompressed

            if (memoryProtection != other.memoryProtection) return false
            if (!rawContent.contentEquals(other.rawContent)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = memoryProtection.hashCode()
            result = 31 * result + rawContent.contentHashCode()
            return result
        }


        @OptIn(ExperimentalEncodingApi::class)
        override fun serialize(id: Int) = Element(BINARY).apply {
            attr(attributeKey = ID, attributeValue = id.toString())
            attr(
                attributeKey = COMPRESSED,
                attributeValue = false.toXmlString(),
            )
            text(Base64.encode(rawContent))
        }
    }

    data class Compressed(
        override val rawContent: ByteArray,
        override val memoryProtection: Boolean,
    ) : BinaryData(
        rawContent.toByteString().sha256(),
    ) {
        override fun getContent(): ByteArray {
            return rawContent.ungzip()
        }


        @OptIn(ExperimentalEncodingApi::class)
        override fun serialize(id: Int) = Element(BINARY).apply {
            attr(attributeKey = ID, attributeValue = id.toString())
            attr(
                attributeKey = COMPRESSED,
                attributeValue = true.toXmlString(),
            )
            text(Base64.encode(rawContent))
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false
            if (!super.equals(other)) return false

            other as Compressed

            if (!rawContent.contentEquals(other.rawContent)) return false
            if (memoryProtection != other.memoryProtection) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + rawContent.contentHashCode()
            result = 31 * result + memoryProtection.hashCode()
            return result
        }
    }


    companion object {

        private const val ID = "id"
        private const val COMPRESSED = "compressed"
        private const val BINARY = "Binary"
        private const val BINARIES = "Binaries"
    }
}
