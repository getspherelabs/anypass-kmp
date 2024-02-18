import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SecureRandomTest {

    private lateinit var secureRandom: SecureRandom

    @BeforeTest
    fun setup() {
        secureRandom = buildSecureRandom()
    }

    @Test
    fun `GIVEN the size is 1 WHEN calls nextBytes THEN it is not null`() {
        // GIVEN
        val size = 100

        // WHEN
        val result = secureRandom.nextBytes(size).toList()

        // THEN
        assertNotNull(result)
    }

    @Test
    fun `GIVEN the bytes WHEN runs the nextBytes THEN it is not null`() {
        // GIVES
        val bytes = ByteArray(32)

        // WHEN
        val result = secureRandom.nextBytes(bytes)

        // THEN
        assertNotNull(result)
    }

    @Test
    fun `GIVEN the empty bytes WHEN runs the next bytes THEN checks is empty`() {
        // GIVES
        val bytes = byteArrayOf()

        // WHEN
        val result = secureRandom.nextBytes(bytes)

        // THEN
        assertEquals(expected = result.isEmpty(), actual = bytes.isEmpty())
    }

    @Test
    fun `GIVEN the size is 1 to bytes WHEN runs the next bytes THEN checks size`() {
        // GIVEN
        val bytes = ByteArray(1)

        // WHEN
        val result = secureRandom.nextBytes(bytes)

        // THEN
        assertEquals(expected = result.size, actual = bytes.size)
    }
}
