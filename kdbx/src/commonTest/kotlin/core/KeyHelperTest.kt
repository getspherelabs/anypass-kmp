package core

import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import io.spherelabs.crypto.tinypass.database.core2.KeyHelper
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class KeyHelperTest {

    private lateinit var secureRandom: SecureRandom

    @BeforeTest
    fun setup() {
        secureRandom = buildSecureRandom()
    }

    @Test
    fun `GIVEN the credentials xml file WHEN parse the input THEN checks it is not null `() {
        // GIVEN
        val random = secureRandom.nextBytes(32)

        // WHEN
        val keyHelper = KeyHelper.from(htmlContent.encodeToByteArray())
        val xml = xmlParser(content = KeyHelper.createKeyfile(htmlContent.encodeToByteArray()))

        // THEN
        assertNotNull(xml)
    }

    @Test
    fun `GIVEN the key WHEN create key file THEN encoding hex`() {
        // GIVEN
        val key = secureRandom.nextBytes(32)

        // THEN
        val file = KeyHelper.createKeyfile(key)

        val keyHelper = KeyHelper.from(file.encodeToByteArray())
        val hex = keyHelper.key!!.raw.encodeHex()

        // WHEN
        assertEquals(hex, key.encodeHex())
    }

    @Test
    fun `GIVEN the key WHEN create key file THEN encoding hex is not same`() {
        // GIVEN
        val key = secureRandom.nextBytes(32)
        val actualHex = "werwerxkjdi123asdjksd"

        // THEN
        val file = KeyHelper.createKeyfile(key)
        val keyHelper = KeyHelper.from(file.encodeToByteArray())
        val hex = keyHelper.key!!.raw.encodeHex()

        // WHEN
        assertNotEquals(hex, actualHex)
    }

    companion object {
        private val htmlContent = """
        <keyfile>
            <meta>
            <version>1.0</version></meta>
            <key>
            <data>ASDJAsjkskjRIER23423jdkljs</data>
            </key>
        </keyfile>
    """.trimIndent()
    }
}
