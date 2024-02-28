package core

import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.model.component.SecureBytes
import kotlin.experimental.xor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SecureBytesTest {

    @Test
    fun `GIVEN the plain text WHEN create encryptor THEN the checks the plain text is not same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform_s"
        val expected = secureBytes(plainText)

        assertNotEquals(expected.plainText, actual)
    }

    @Test
    fun `GIVEN the plain text WHEN encrypted THEN the checks the plain text is same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform"
        val expected = secureBytes(plainText)

        assertEquals(expected.plainText, actual)
    }

    @Test
    fun `GIVEN the plain text WHEN decoding THEN size is the same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform"

        val expected = secureBytes(plainText)

        assertEquals(expected.count, actual.length)
    }

    @Test
    fun `GIVEN the plain text WHEN decoding THEN size is not the same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform_2"

        val expected = secureBytes(plainText)

        assertNotEquals(expected.count, actual.length)
    }

    @Test
    fun `GIVEN the plain text WHEN decoding THEN sha256 is the same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform".encodeToByteArray().sha256().encodeHex()

        val expected = secureBytes(plainText).sha256.encodeHex()
        assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN the plain text WHEN decoding THEN sha512 is the same`() {
        val plainText = "anypass_multiplatform"
        val actual = "anypass_multiplatform".encodeToByteArray().sha512().encodeHex()

        val expected = secureBytes(plainText).sha512.encodeHex()
        assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN the empty text WHEN decoding THEN checks empty`() {
        val plainText = ""
        val actual = ""

        val expected = secureBytes(plainText)
        assertEquals(expected.isEmpty, actual.isEmpty())
    }

    private fun secureBytes(plainText: String): SecureBytes {
        val rawValue = plainText.encodeToByteArray()
        val bytes = plainText.encodeToByteArray()
        val salt = ByteArray(rawValue.size)
        for (i in salt.indices) {
            salt[i] = i.toByte()
            bytes[i] = bytes[i] xor i.toByte()
        }

        return SecureBytes(bytes, salt)
    }

}
