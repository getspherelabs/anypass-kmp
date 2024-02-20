package buffer

import io.spherelabs.crypto.tinypass.database.buffer.KdbxInput
import io.spherelabs.crypto.tinypass.database.buffer.KdbxOutput
import io.spherelabs.crypto.tinypass.database.buffer.KdbxSink
import io.spherelabs.crypto.tinypass.database.buffer.KdbxSource
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import kotlin.test.Test
import okio.Buffer

class KdbxSourceTest {

    @Test
    fun `GIVEN AND WHEN`() {
        val source = Buffer()
        val outerHeader = KdbxOutput.OuterHeader(KdbxOuterHeader.create())

        val kdbxSource = KdbxSink.write(
            sink = source,
            outerHeader,
        )

        val data = KdbxSource.read(source, KdbxInput.OuterHeader)

        println(data)
    }
}
