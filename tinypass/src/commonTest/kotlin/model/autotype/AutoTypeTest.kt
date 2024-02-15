package model.autotype


import io.spherelabs.crypto.tinypass.database.model.autotype.AutoType
import io.spherelabs.crypto.tinypass.database.model.autotype.AutoTypeItem
import io.spherelabs.crypto.tinypass.database.model.autotype.Obfuscation
import kotlin.test.*

class AutoTypeTest {

    @Test
    fun `GIVEN the  auto type WHEN serialized THEN checks the results deserialized properly `() {
        val actual = AutoType(
            enabled = true,
            obfuscation = Obfuscation.UseClipboard,
            defaultSequence = "12345",
            items = listOf(
                AutoTypeItem(
                    window = "chrome",
                    keystrokeSequence = "PSS",
                ),
            ),
        )

        val result = actual.serialize()
        val expected = AutoType.deserialize(result)

        println("Result is $result")
        assertEquals(expected.enabled,actual.enabled)
        assertEquals(expected.obfuscation, actual.obfuscation)
    }

    @Test
    fun `GIVEN the  auto type WHEN serialized THEN checks not existed items `() {
        val actual = AutoType(
            enabled = true,
            obfuscation = Obfuscation.UseClipboard,
            defaultSequence = "12345",
        )

        val result = actual.serialize()
        val expected = AutoType.deserialize(result)

        assertTrue(expected.items.isEmpty())
    }


    @Test
    fun `GIVEN the  auto type WHEN serialized THEN checks the sequence is null `() {
        val actual = AutoType(
            enabled = true,
            obfuscation = Obfuscation.UseClipboard,
        )

        val result = actual.serialize()
        val expected = AutoType.deserialize(result)

        expected.defaultSequence?.isEmpty()?.let { assertTrue(it) }
    }
}
