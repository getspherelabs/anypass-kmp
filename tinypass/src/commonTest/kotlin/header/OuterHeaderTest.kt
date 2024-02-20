package header

import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import kotlin.test.Test
import okio.Buffer

class OuterHeaderTest {

    @Test
    fun `GIVEN the data THEN reads key derivation WHEN checks the extracting properly`() {
        val buffer = Buffer()
        KdbxOuterHeader.create().serialize(buffer)
        val data = KdbxOuterHeader.deserialize(buffer)

        println(data)
    }
}
