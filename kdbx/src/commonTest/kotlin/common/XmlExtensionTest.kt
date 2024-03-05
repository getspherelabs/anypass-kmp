package common

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.parser.Parser
import com.fleeksoft.ksoup.ported.BufferReader
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.common.xml
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import okio.Buffer
import okio.ByteString.Companion.toByteString

class XmlExtensionTest {

    @Test
    fun `GIVEN version and charset THEN create xml document THEN checks the content is not empty`() {
        val version = "2.0"
        val encoding = "utf-8"

        val document = xml(version, encoding) {
            appendElement("meta").apply {
                appendElement("version").text("2.0")
            }
        }
        println(document)
        assertNotNull(document)
    }

    @Test
    fun `GIVEN the html content THEN parse to xml WHEN checks the content is not empty`() {
        val buffer = Buffer().write(htmlContent.encodeToByteArray())

        val parser = Ksoup.parse(
            bufferReader = BufferReader(buffer),
            baseUri = "",
            charsetName = null,
            parser = Parser.xmlParser(),
        )

       println(parser)

        assertNotNull(parser)
    }

    @Test
    fun `GIVEN the html content THEN parse to xml WHEN checks the content empty`() {
        val parser = xmlParser("")

        assertEquals(parser.toString(), "")
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
