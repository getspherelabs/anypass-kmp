import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotEmpty
import io.spherelabs.common.uuid4
import kotlin.test.Test

class UuidTest {

    @Test
    fun `check the uuid works properly`() {
        val uuid = uuid4()

        assertThat(uuid).isNotEmpty()
        assertThat(uuid.length).isGreaterThan(10)
    }
}
