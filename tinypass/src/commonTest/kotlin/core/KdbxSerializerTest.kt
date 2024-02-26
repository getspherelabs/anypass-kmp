package core

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import kotlin.test.Test

class KdbxSerializerTest {

    @Test
    fun test() {
        val serializer = KdbxSerializer.encode(
            config = KdbxConfiguration.of(
                passphrase = "encode",
                path = "test.kdbx"
            )
        )

        println(serializer)
    }
}
