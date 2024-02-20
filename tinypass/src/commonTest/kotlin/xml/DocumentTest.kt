package xml

import com.fleeksoft.ksoup.Ksoup
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import kotlin.test.Test

class DocumentTest {

    @Test
    fun `GIVEN the root WHEN select root THEN it returns root`() {
        val document = xmlParser(htmlContent)


    }

    companion object {
        private val htmlContent = """
        <?xml version="1.0" encoding="UTF-8"?>
        <root>
            <meta>
            <version>1.0</version></meta>
            <key>
            <data>ASDJAsjkskjRIER23423jdkljs</data>
            </key>
        </root>
    """.trimIndent()
    }
}
