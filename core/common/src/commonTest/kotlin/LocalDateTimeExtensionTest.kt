import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.common.formatLongTime
import kotlin.test.Test

class LocalDateTimeExtensionTest {
    @Test
    fun `GIVEN the current time WHEN format to string THEN checks the right timestamp`() {
        val now = 1704760525488

        val instant = now.formatLongTime()
        val currentTimestamp = "2024/1/9 9:35:25"

        assertThat(instant).isEqualTo(currentTimestamp)
    }
}
