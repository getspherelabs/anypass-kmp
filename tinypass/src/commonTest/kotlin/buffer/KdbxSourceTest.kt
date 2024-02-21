package buffer

import io.spherelabs.crypto.tinypass.database.buffer.*
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import kotlin.test.Test
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource

class KdbxSourceTest {

    @Test
    fun `GIVEN AND WHEN`() {
        val outerHeader = KdbxOuterHeader.create()

        KdbxBuffer.writeOuterHeader(outerHeader)

        val data = KdbxBuffer.readOuterHeader()

        println(data)
    }
}
