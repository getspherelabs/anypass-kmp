package header

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.cipher.Argon2Engine
import io.spherelabs.crypto.tinypass.database.header.OuterHeader
import kotlin.test.Test
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import okio.buffer

class OuterHeaderTest {

    @Test
    fun `GIVEN the data THEN reads key derivation WHEN checks the extracting properly`() {
        val buffer = Buffer()
        OuterHeader.create().serialize(buffer)
        val data = OuterHeader.deserialize(buffer)

        println(data)
    }
}
