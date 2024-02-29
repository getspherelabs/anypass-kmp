package core

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.kdbx
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class KdbxBuilderTest {

    @Test
    fun test() = runTest {
        val configuration = KdbxConfiguration.of(
            path = "resource/config.kdbx",
            passphrase = "kdbx_multiplatform",
        )

        val kdbx = kdbx(configuration)

        kdbx.open()

        val query = kdbx.read("kdbx_multiplatform").query


        println("Read data: ${query.meta}")
    }
}
