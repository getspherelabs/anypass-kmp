import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.header.FieldStorage
import io.spherelabs.crypto.tinypass.database.header.Kdbx4Field
import io.spherelabs.crypto.tinypass.database.header.Type
import kotlin.math.sin
import kotlin.test.Test
import kotlin.test.assertEquals
import okio.*
import okio.ByteString.Companion.toByteString

class FieldStorageTest {

    @Test
    fun `GIVE write some data WHEN use field storage THEN checks existing the written data`() {
        val bytes = buildSecureRandom().nextBytes(32)


        val input = mutableMapOf(
            "uuid" to Kdbx4Field.Bytes(bytes.toByteString()),
        )

        val result = FieldStorage.write(input).let {
            FieldStorage.read(it)
        }

        val uuid = result["uuid"]

        assertEquals(uuid?.type, Type.Bytes)
    }
}

enum class ID {
    BlahBlah,
    AAA
}
