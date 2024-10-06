import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.Padding
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AESTest {

    private lateinit var random: SecureRandom

    @BeforeTest
    fun setup() {
        random = buildSecureRandom()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN the cipher key and iv WHEN no padding THEN checks encrypting and decrypting the plain text`() {
        val padding = Padding.NoPadding
        val cipherKey = random.nextBytes(32)
        val iv = random.nextBytes(16)

        val plainText = random.nextBytes(16)

        val result = AES.encryptAesCbc(plainText, cipherKey, iv, padding)
        val decryptedResult = AES.decryptAesCbc(result, cipherKey, iv, padding)

        assertEquals(plainText.toHexString(), decryptedResult.toHexString())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN the cipher key and iv WHEN  padding is PKCS7 THEN checks encrypting and decrypting the plain text`() {
        val padding = Padding.PKCS7Padding
        val cipherKey = random.nextBytes(32)
        val iv = random.nextBytes(24)

        val plainText = random.nextBytes(24)

        val result = AES.encryptAesCbc(plainText, cipherKey, iv, padding)
        val decryptedResult = AES.decryptAesCbc(result, cipherKey, iv, padding)

        assertEquals(plainText.toHexString(), decryptedResult.toHexString())
    }
}
