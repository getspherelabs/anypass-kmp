package common

import io.spherelabs.crypto.tinypass.database.common.xml
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

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
        assertNotNull(document)
    }

    @Test
    fun `GIVEN the html content THEN parse to xml WHEN checks the content is not empty`() {
        val parser = xmlParser(htmlContent)

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
