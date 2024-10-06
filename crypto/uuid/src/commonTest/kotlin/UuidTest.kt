import io.spherelabs.crypto.uuid.Uuid
import io.spherelabs.crypto.uuid.uuid4
import kotlin.test.*

class UuidTest {

    private lateinit var uuid: Uuid

    @BeforeTest
    fun setup() {
       uuid = uuid4()
    }

    @Test
    fun `GIVEN create uuid WHEN generate random uuid THEN checks it is not null`() {
        val result = uuid.toString()

        assertNotNull(result)
    }

    @Test
    fun `GIVEN create uuid WHEN generate random uuid THEN consists 32 hexadecimal digits`() {
        val result = uuid.toString()

        assertEquals(36, result.length)
        assertTrue { !uuid.isZero() }
    }
}
