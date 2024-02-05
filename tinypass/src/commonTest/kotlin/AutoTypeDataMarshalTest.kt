import io.spherelabs.crypto.tinypass.database.AutoTypeData
import io.spherelabs.crypto.tinypass.database.AutoTypeItem
import io.spherelabs.crypto.tinypass.database.AutoTypeObfuscation
import io.spherelabs.crypto.tinypass.database.xml.deserializeToAutoType
import io.spherelabs.crypto.tinypass.database.xml.serialize
import kotlin.test.Test
import kotlin.test.assertEquals

class AutoTypeDataMarshalTest {

    @Test
    fun `GIVEN the xml file WHEN deserialize THEN equals proper data`() {
        val actual = AutoTypeData(
            enabled = true,
            obfuscation = AutoTypeObfuscation.UseClipboard,
            defaultSequence = "12345",
            items = listOf(
                AutoTypeItem(
                    window = "chrome",
                    keystrokeSequence = "PSS",
                ),
            ),
        )

        val result = actual.serialize()
        val expected = result.deserializeToAutoType()

        assertEquals(actual.enabled, expected.enabled)
        assertEquals(actual.obfuscation, expected.obfuscation)
        assertEquals(actual.defaultSequence, expected.defaultSequence)
    }
}
