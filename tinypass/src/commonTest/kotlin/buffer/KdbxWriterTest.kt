package buffer

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import okio.ByteString.Companion.toByteString

class KdbxWriterTest {

    @Test
    fun `GIVEN the inner header WHEN writes to buffer THEN checks is not null`() {
        val random = buildSecureRandom().nextBytes(64).toByteString()
        val innerHeader = KdbxInnerHeader.of(random)

        KdbxBuffer.writeInnerHeader(innerHeader)
        val expected = KdbxBuffer.readInnerHeader()

        assertNotNull(expected)
    }

    @Test
    fun `GIVEN the inner header WHEN writes to buffer THEN checks the same`() {
        val random = buildSecureRandom().nextBytes(64).toByteString()
        val innerHeader = KdbxInnerHeader.of(random)

        KdbxBuffer.writeInnerHeader(innerHeader)
        val expected = KdbxBuffer.readInnerHeader()

        assertEquals(expected, innerHeader)
    }

    @Test
    fun `GIVEN the size is 0 WHEN writes to buffer THEN returns 0 `() {
        val random = buildSecureRandom().nextBytes(0).toByteString()
        val innerHeader = KdbxInnerHeader.of(random)

        KdbxBuffer.writeInnerHeader(innerHeader)
        val expected = KdbxBuffer.readInnerHeader()

        assertTrue(expected.streamKey.size == 0)
    }
}
