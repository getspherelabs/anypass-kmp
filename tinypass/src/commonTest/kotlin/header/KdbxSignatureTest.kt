package header

import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import kotlin.test.Test

class KdbxSignatureTest {

    @Test
    fun `GIVEN the default signature WHEN writes to buffer THEN reads from buffer`() {
        val actual = KdbxSignature.Default

        KdbxBuffer.writeSignature(actual)

        val data: KdbxSignature = KdbxBuffer.readSignature()

        println(data)
    }
}
