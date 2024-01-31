import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.hash.Algorithm
import io.spherelabs.crypto.hash.Digest
import io.spherelabs.crypto.hash.digest
import kotlin.test.*

class Sha256Test {

    private lateinit var sha256: Digest
    private lateinit var random: SecureRandom

    @BeforeTest
    fun setup() {
        sha256 = digest(Algorithm.Sha256)
        random = buildSecureRandom()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN sha256 WHEN the buffer is anypass THEN it returns hash`() {
        val buffer = random.nextBytes(10)

        sha256.digest(buffer)

        val expected = sha256.digest()

        assertNotNull(expected.toHexString())
        assertTrue(expected.toHexString().length == 64)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN sha512 WHEN the buffer is anypass THEN the length equals 64`() {
        val buffer = random.nextBytes(10)

        sha256.digest(buffer)

        val expected = sha256.digest()

        assertTrue(expected.toHexString().length == 64)
    }
}
