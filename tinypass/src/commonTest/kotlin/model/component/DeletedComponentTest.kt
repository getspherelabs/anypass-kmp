package model.component

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.tinypass.database.model.component.DeletedComponent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.datetime.Clock

class DeletedComponentTest {

    @Test
    fun `GIVEN the deleted component WHEN serialized THEN checks the uuid is null`() {
        val actual = DeletedComponent(
            null,
            timestamp = Clock.System.now(),
        )
        val result = actual.serialize()
        val expected = DeletedComponent.deserialize(result)

       assertNull(expected.id)
    }

    @Test
    fun `GIVEN the deleted component WHEN serialized THEN checks the uuid is same`() {
        val actual = DeletedComponent(
            id = uuid4(),
            timestamp = Clock.System.now(),
        )
        val result = actual.serialize()
        val expected = DeletedComponent.deserialize(result)

        assertNotNull(expected)
        assertEquals(actual.id, expected.id)
    }


}
