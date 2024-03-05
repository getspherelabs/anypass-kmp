@file:OptIn(ExperimentalEncodingApi::class)

package model.component

import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryDataTest {

    @Test
    fun `GIVEN the xml file THEN serialized uncompressed WHEN checks the correct format`() {
        val content =
            BinaryData.Uncompressed(memoryProtection = false, CONTENT.encodeToByteArray())
        val result = content.serialize(1)
        val expected = BinaryData.deserialize(result)

        assertEquals(
            Base64.encode(expected.second.rawContent),
            Base64.encode(content.rawContent),
        )
    }

    @Test
    fun `GIVEN the xml file THEN serialized compressed WHEN checks the correct format`() {
        val content = BinaryData.Compressed(
            memoryProtection = false,
            rawContent = CONTENT.encodeToByteArray(),
        )

        val result = content.serialize(1)

        val expected = BinaryData.deserialize(result)

        assertEquals(
            Base64.encode(expected.second.rawContent),
            Base64.encode(content.rawContent),
        )
    }

    @Test
    fun `GIVEN the xml file THEN serialized and converted to compressed WHEN checks the correct format`() {
        val content =
            BinaryData.Uncompressed(memoryProtection = false, CONTENT.encodeToByteArray())

        println("Content is ${content.rawContent.toList()}")

        content.toCompressed()

        println("Content is ${content.rawContent.toList()}")
        val result = content.serialize(1)
        val expected = BinaryData.deserialize(result)

        assertEquals(
            Base64.encode(expected.second.rawContent),
            Base64.encode(content.rawContent),
        )
    }

    @Test
    fun `GIVEN the xml file THEN serialized elements WHEN checks the correct format`() {
        val content =
            BinaryData.Uncompressed(memoryProtection = false, CONTENT.encodeToByteArray())

        val result = content.serialize(1)
        val expected = BinaryData.deserializeElements(result)

        val data = expected[content.hash]?.rawContent ?: byteArrayOf()

        assertEquals(
            expected = Base64.encode(data),
            actual = Base64.encode(content.rawContent),
        )
    }

    companion object {
        private const val CONTENT = "anypass"
    }
}
