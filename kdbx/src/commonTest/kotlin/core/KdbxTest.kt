package core

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.kdbx.database.BasicField
import io.spherelabs.crypto.kdbx.database.core.internal.find
import io.spherelabs.crypto.kdbx.database.core.internal.findEntity
import io.spherelabs.crypto.kdbx.database.core.internal.meta
import io.spherelabs.crypto.kdbx.database.core.internal.updateParentGroup
import io.spherelabs.crypto.kdbx.database.core.kdbx
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import io.spherelabs.crypto.kdbx.database.model.component.EntryFields
import io.spherelabs.crypto.kdbx.database.model.component.EntryValue
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.runTest

class KdbxTest {


    @Test
    fun `GIVEN the kdbx config WHEN kdbx open THEN empty is true`() = runTest {
        val kdbx = kdbx {
            path = "config3.kdbx"
            passphrase = "test_1"
        }

        assertTrue { kdbx.isEmpty() }
    }

    @Test
    fun test() = runTest(
        timeout = 60.seconds,
    ) {
        val kdbx = kdbx  {
            path = "config2.kdbx"
            passphrase  = "kdbx_multiplatform"
        }

        kdbx.open()

        val db =
            kdbx.read("kdbx_multiplatform")

        println("Meta is ${db.meta}")

        println("Boolean value = ${kdbx.isEmpty()}")



        val query = db.query.updateParentGroup {
           val entry = Entry(
               id = uuid4(),
               fields = EntryFields.of(
                   BasicField.Title.key to EntryValue.Plain("Behzod"),
                   BasicField.Notes.key to EntryValue.Plain("Behzod2")
               ),
               customData = mapOf()
           )

            println("Entry = $entry")
            copy(entries = entries + entry, notes = "12323")
        }

        kdbx.write(query)



        val db2 = kdbx.read()


        assertNotNull(db2)
    }
}
/**
 *     val newDatabase = database.modifyParentGroup {
 *       val entry = Entry(
 *         uuid = uuid,
 *         fields = EntryFields.of(
 *           BasicField.Title.key to EntryValue.Plain(noteEntryData.title),
 *           BasicField.Notes.key to EntryValue.Plain(noteEntryData.text)
 *         ),
 *         customData = mapOf(
 *           CUSTOM_DATA_TYPE_KEY to CustomDataValue(CUSTOM_DATA_NOTE),
 *           CUSTOM_DATA_FAVORITE_KEY to noteEntryData.isFavorite.toValue(instantProvider.now()),
 *         )
 *       )
 *       copy(entries = entries + entry)
 *     }
 */
