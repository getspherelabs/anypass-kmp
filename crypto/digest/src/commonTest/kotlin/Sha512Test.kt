import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.hash.Algorithm
import io.spherelabs.crypto.hash.Digest
import io.spherelabs.crypto.hash.digest
import kotlin.test.*

class Sha512Test {

    private lateinit var sha512: Digest
    private lateinit var random: SecureRandom

    @BeforeTest
    fun setup() {
        sha512 = digest(Algorithm.Sha512)
        random = buildSecureRandom()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN sha512 WHEN the buffer is anypass THEN it returns hash`() {
        val buffer = random.nextBytes(10)

        sha512.digest(buffer)

        val expected = sha512.digest()

        assertNotNull(expected.toHexString())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN sha512 WHEN the buffer is anypass THEN the length equals 128`() {
        val buffer = random.nextBytes(10)

        sha512.digest(buffer)

        val expected = sha512.digest()

        assertTrue(expected.toHexString().length == 128)
    }
}
