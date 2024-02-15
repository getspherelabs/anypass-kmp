package core

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.common.encodeHex
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import io.spherelabs.crypto.tinypass.database.core.KeyHelper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class KeyHelperTest {

    @Test
    fun `GIVEN the credentials xml file THEN parse the input WHEN checks the parsing input correctly `() {
        val random = buildSecureRandom().nextBytes(32)

        val keyHelper = KeyHelper.from(htmlContent.encodeToByteArray())
        val xml = xmlParser(content = htmlContent)

        assertNotNull(xml)
    }

    @Test
    fun `GIVEN the key THEN create key file WHEN encoding hex`() {
        val key = buildSecureRandom().nextBytes(32)
        val file = KeyHelper.createKeyfile(key)

        val keyHelper = KeyHelper.from(file.encodeToByteArray())
        val hex = keyHelper.key!!.raw.encodeHex()

        assertEquals(hex, key.encodeHex())
    }

    @Test
    fun `GIVEN the key THEN create key file WHEN encoding hex is not same`() {
        val key = buildSecureRandom().nextBytes(32)
        val file = KeyHelper.createKeyfile(key)

        val keyHelper = KeyHelper.from(file.encodeToByteArray())
        val hex = keyHelper.key!!.raw.encodeHex()

        assertNotEquals(hex, "werwerxkjdi123asdjksd")
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
