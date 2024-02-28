package core

import io.spherelabs.crypto.tinypass.database.core2.KdbxBuilder
import io.spherelabs.crypto.tinypass.database.core2.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core2.kdbx
import kotlin.test.Test

class KdbxSerializerTest {

    @Test
    fun test() {
        val configuration = KdbxConfiguration.of(
            path = "",
            passphrase = "kdbx_multiplatform",
        )

        val kdbx = kdbx(configuration)
        val builder = KdbxBuilder(configuration)

        val db = kdbx.write()
        val query = kdbx.read().query


        println("Read data: $query")
        println("Database is $db")
    }
}
