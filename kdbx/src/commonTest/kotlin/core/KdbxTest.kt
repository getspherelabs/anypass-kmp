package core

import io.spherelabs.crypto.tinypass.database.core.Kdbx
import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.internal.find
import io.spherelabs.crypto.tinypass.database.core.internal.meta
import io.spherelabs.crypto.tinypass.database.core.internal.updateWith
import io.spherelabs.crypto.tinypass.database.core.kdbx
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.runTest

class KdbxTest {

    @Test
    fun test() = runTest(
        timeout = 60.seconds,
    ) {
        val configuration = KdbxConfiguration.of(
            path = "config.kdbx",
            passphrase = "kdbx_multiplatform",
        )

        val kdbx: Kdbx = kdbx(configuration)

        kdbx.open()

        val db =
            kdbx.read("kdbx_multiplatform")

        println("Meta is ${db.meta}")

        db.query.find {
            println("Entity is $it")
        }
        assertNotNull(db)
    }
}
