package core

import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.core.internal.HmacBlock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import okio.Buffer

class HmacBlockTest {

    private lateinit var random: SecureRandom
    private val buffer: Buffer by lazy { Buffer() }

    @BeforeTest
    fun setup() {
        random = buildSecureRandom()
    }

    @Test
    fun `GIVEN random seed and key WHEN writes to hmac block THEN checks same data`() {
        val seed = random.nextBytes(32)
        val key = random.nextBytes(32)

        val kafka =
            "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he"

        HmacBlock.write(
            sink = buffer,
            bytes = kafka.encodeToByteArray(),
            seed = seed,
            transformedKey = key,
        )

        val expected =
            HmacBlock.read(source = buffer, seed = seed, transformedKey = key).decodeToString()

        assertEquals(expected, kafka)
    }

    @Test
    fun `GIVEN random seed and key WHEN writes to hmac block THEN checks not same data`() {
        val seed = random.nextBytes(32)
        val key = random.nextBytes(32)

        val kafka =
            "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he"

        HmacBlock.write(
            sink = buffer,
            bytes = kafka.encodeToByteArray(),
            seed = seed,
            transformedKey = key,
        )

        val actual =
            "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin."
        val expected =
            HmacBlock.read(source = buffer, seed = seed, transformedKey = key).decodeToString()

        assertNotEquals(expected, actual)
    }
}
