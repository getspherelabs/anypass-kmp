import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.header.VarDict
import io.spherelabs.crypto.tinypass.database.header.Kdbx4Field
import io.spherelabs.crypto.tinypass.database.header.KdfParameters
import io.spherelabs.crypto.tinypass.database.header.Type
import kotlin.test.Test
import kotlin.test.assertEquals
import okio.ByteString.Companion.toByteString

class VarDictTest {

    @Test
    fun `GIVE write some data WHEN use field storage THEN checks existing the written data`() {
        val bytes = buildSecureRandom().nextBytes(32)


        val input = mutableMapOf(
            KdfParameters.UUID to Kdbx4Field.Bytes(bytes.toByteString()),
        )

        val result = VarDict.serialize(input).let {
            VarDict.deserialize(it)
        }

        val uuid = result[KdfParameters.UUID]

        assertEquals(uuid?.type, Type.Bytes)
    }

}
