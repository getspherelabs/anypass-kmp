import com.benasher44.uuid.bytes
import com.benasher44.uuid.uuid
import com.benasher44.uuid.uuidFrom
import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toLongLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import okio.ByteString
import okio.ByteString.Companion.toByteString

class ByteStringExtensionTest {

    @Test
    fun `GIVEN the byte string WHEN converts to integer length THEN equals the same`() {
        val actual = ByteString.of(0x01, 0x02, 0x03, 0x04)

        val result = actual.toIntLe()

        val expected = 0x04030201

        assertEquals(expected, result)
    }


    @Test
    fun `GIVEN byte string WHEN convert to integer length THEN equals not same`() {
        val actual = ByteString.of(0x01, 0x02, 0x03, 0x04)

        val result = actual.toIntLe()

        val expected = 0x01020304

        assertNotEquals(expected, result)
    }

    @Test
    fun `GIVEN the byte string WHEN converts to long length THEN equals the same`() {
        val actual = ByteString.of(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08)

        val result = actual.toLongLe()

        val expected = 0x0807060504030201

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN byte string WHEN convert to long length THEN equals not same`() {
        val actual = ByteString.of(0x01, 0x02, 0x03, 0x04)

        val result = actual.toLongLe()

        val expected = 0x0102030405060708

        assertNotEquals(expected, result)
    }

    @Test
    fun `GIVEN the byte string WHEN convert to uuid THEN equals same`() {
        val uuid = uuidFrom("31c1f2e6-bf71-4350-be58-05216afc5aff")
        val bytes = uuid.bytes

        val actual =
            bytes.toByteString()

        val expected = actual.toUuid()

        assertEquals(uuid, expected)
    }
}
