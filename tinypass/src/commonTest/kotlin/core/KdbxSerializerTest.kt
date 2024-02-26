package core

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import kotlin.test.Test

class KdbxSerializerTest {

    @Test
    fun test() {
        val configuration = KdbxConfiguration.of(
            path = "test4.kdbx",
            passphrase = "kdbx_multiplatform",
        )

        val database = KdbxDatabase.write(configuration)

        println(KdbxDatabase.isEmpty("test.kdbx"))
        println(KdbxDatabase.isEmpty("test1.kdbx"))
        println(database)
    }
}
