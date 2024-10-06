package core

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.kdbx.database.EntryReferenceType
import io.spherelabs.crypto.kdbx.database.core.Kdbx
import io.spherelabs.crypto.kdbx.database.core.KdbxDatabase
import io.spherelabs.crypto.kdbx.database.core.internal.updateParentGroup
import io.spherelabs.crypto.kdbx.database.core.kdbx
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.model.component.EntryAttributes
import io.spherelabs.crypto.kdbx.database.model.component.EntryValue
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.runTest

class KdbxTest {

    private var database: KdbxDatabase? = null
    private lateinit var kdbx: Kdbx

    @BeforeTest
    fun setup() {
        kdbx = kdbx  {
            path = PATH
            passphrase  = "kdbx_multiplatform"
        }
    }

    @Test
    fun `GIVEN the kdbx config WHEN kdbx open THEN empty is true`() = runTest {
        val kdbx = kdbx {
            path = "config3.kdbx"
            passphrase = "test_1"
        }

        assertTrue { kdbx.isEmpty() }
    }

    @Test
    fun `GIVEN the kdbx config WHEN reads kdbx file THEN update and add entry`() = runTest(
        timeout = 60.seconds,
    ) {


        val title = "Google"
        val note = "Kdbx is encrypted database."
        kdbx.open()

        database =
            kdbx.read("kdbx_multiplatform")

        val query = database?.query?.updateParentGroup {
           val entry = Entry(
               id = uuid4(),
               fields = EntryAttributes.of(
                   EntryReferenceType.Title.key to EntryValue.Plain(title),
                   EntryReferenceType.Notes.key to EntryValue.Plain(note)
               ),
               customData = mapOf()
           )

            copy(entries = entries + entry, notes = "12323")
        }

        kdbx.write(query)


        database = kdbx.read()

        val data = database?.query?.group?.entries

        assertNotNull(data)
        assertEquals(1, data.size)
        assertNotEquals(0, data.size)
        assertEquals(title,data.first().fields.title?.content)
        assertEquals(note,data.first().fields.notes?.content)
    }

    companion object {
        private const val PATH = "config2.kdb"
    }
}
