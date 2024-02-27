package core

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import io.spherelabs.crypto.tinypass.database.serializer.internal.commonKdbxEncode
import kotlin.test.Test
import okio.Buffer
import okio.BufferedSink
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

class KdbxSerializerTest {

    @Test
    fun test() {
        val configuration = KdbxConfiguration.of(
            path = "config.kdbx",
            passphrase = "kdbx_multiplatform",
        )
        val configuration2 = KdbxConfiguration.of(
            path = "config2.kdbx",
            passphrase = "kdbx_multiplatform",
        )

        val buffer = Buffer()
        val buffer2 = Buffer()
        val data = KdbxSerializer.encode(buffer, configuration)
        val readData = KdbxSerializer.decode(buffer, data)

        val sink = getPlatformFileSystem().sink(configuration2.path.toPath(), true).use { sink ->
            KdbxSerializer.encode(sink.buffer().buffer, configuration)
        }

        println("Database is $sink")
        println("Buffer is $data")
        println("Read data is $readData")
    }
}
