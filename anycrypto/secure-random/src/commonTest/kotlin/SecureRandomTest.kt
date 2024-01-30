import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import kotlin.test.Test
import kotlin.test.assertNotNull

class SecureRandomTest {

    @Test
    fun `GIVEN the size is 1 WHEN calls nextBytes THEN it is not null`() {
        val secureRandom: SecureRandom = buildSecureRandom()

        val result = secureRandom.nextBytes(100).toList()

        assertNotNull(result)
    }
}
