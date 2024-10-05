package buffer

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.buffer.WriterStrategy
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import kotlin.test.*
import okio.Buffer
import okio.ByteString.Companion.toByteString

class KdbxBufferTest {
    private val buffer = Buffer()
    private val random = buildSecureRandom()

    @Test
    fun `GIVEN inner header WHEN write and read THEN checks is not null`() {
        val innerHeader = KdbxInnerHeader.of(
            random.nextBytes(32).toByteString(),
        )
        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Inner(innerHeader),
        )

        val expected = KdbxBuffer.readInnerHeader(buffer)

        assertNotNull(expected)
    }

    @Test
    fun `GIVEN inner header WHEN write THEN read and checks the same key`() {
        val innerHeader = KdbxInnerHeader.of(
            random.nextBytes(32).toByteString(),
        )
        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Inner(innerHeader),
        )

        val expected = KdbxBuffer.readInnerHeader(buffer)

        assertEquals(expected.streamKey, innerHeader.streamKey)
    }

    @Test
    fun `GIVEN inner header WHEN write THEN read and checks the same algorithm`() {
        val innerHeader = KdbxInnerHeader.of(
            random.nextBytes(32).toByteString(),
        )
        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Inner(innerHeader),
        )

        val expected = KdbxBuffer.readInnerHeader(buffer)

        assertEquals(expected.streamCipher, innerHeader.streamCipher)
    }

    @Test
    fun `GIVEN inner header WHEN write THEN checks it is null`() {
        assertFails {
            KdbxBuffer.readInnerHeader(buffer)
        }
    }

    @Test
    fun `GIVEN outer header WHEN write THEN checks it is not null`() {
        val outerHeader = KdbxOuterHeader.of(random)

        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Outer(outerHeader),
        )

        val expected = KdbxBuffer.readOuterHeader(buffer)

        assertNotNull(expected)
    }

    @Test
    fun `GIVEN outer header WHEN write THEN checks it is null`() {
        assertFails("Outer header is empty.") {
            KdbxBuffer.readOuterHeader(buffer)
        }
    }

    @Test
    fun `GIVEN outer header WHEN write THEN checks it is same`() {
        val outerHeader = KdbxOuterHeader.of(random)

        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Outer(outerHeader),
        )

        val expected = KdbxBuffer.readOuterHeader(buffer)

        assertEquals(expected, outerHeader)
    }

    @Test
    fun `GIVEN outer header WHEN write THEN checks it is same seed`() {
        val outerHeader = KdbxOuterHeader.of(random)

        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Outer(outerHeader),
        )

        val expected = KdbxBuffer.readOuterHeader(buffer)

        assertEquals(expected.seed, outerHeader.seed)
    }

    @Test
    fun `GIVEN outer header WHEN write THEN checks it is same compression flag`() {
        val outerHeader = KdbxOuterHeader.of(random)

        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Outer(outerHeader),
        )

        val expected = KdbxBuffer.readOuterHeader(buffer)

        assertEquals(expected.option.compressionFlags, outerHeader.option.compressionFlags)
    }

    @Test
    fun `GIVEN outer and inner header WHEN write THEN checks it is not null`() {
        val outerHeader = KdbxOuterHeader.of(random)
        val innerHeader = KdbxInnerHeader.of(random.nextBytes(32).toByteString())

        KdbxBuffer.write(
            sink = buffer,
            strategy = WriterStrategy.Outer(outerHeader),
        )
        KdbxBuffer.write(buffer, WriterStrategy.Inner(innerHeader))

        val expectedOuter = KdbxBuffer.readOuterHeader(buffer)
        val expectedInner = KdbxBuffer.readInnerHeader(buffer)

        assertNotNull(expectedOuter)
        assertNotNull(expectedInner)
    }
}
