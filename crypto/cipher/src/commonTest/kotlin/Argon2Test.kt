import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.cipher.Argon2
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Argon2Test {

    private lateinit var random: SecureRandom

    @BeforeTest
    fun setup() {
        random = buildSecureRandom()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN the argon2 WHEN generate hash value THEN equals same value`() {
        val randomOutput = random.nextBytes(32)
        val result = "011ed3fc11e41d40bceb3d48e3443417a57a9f7bbfce2c9360ff56956edc9fe4"

        val salt = random.nextBytes(16)

        val plainText = random.nextBytes(32)
        val secret = random.nextBytes(16)
        val additional = random.nextBytes(16)

        Argon2(
            type = Argon2.Type.Argon2Id,
            version = Argon2.Version.Ver13,
            salt = salt,
            secret = secret,
            additional = additional,
            iterations = 3,
            parallelism = 4,
            memory = 32,
        ).encrypt(plainText, randomOutput)

        println(randomOutput.toHexString())
        assertEquals(result, randomOutput.toHexString())
    }
}
