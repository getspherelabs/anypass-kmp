package xml

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.tinypass.database.header.Version
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import io.spherelabs.crypto.tinypass.database.xml.XmlComponent
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlTags
import io.spherelabs.crypto.tinypass.database.xml.XmlWriter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.Clock

class XmlWriterTest {

    @Test
    fun `GIVEN the default meta WHEN write to xml THEN checks the generator is same`() {
        val meta = Meta(
            name = "test.kdbx",
            masterKeyChanged = Clock.System.now(),
            entryTemplatesGroupChanged = Clock.System.now(),
            recycleBinChanged = Clock.System.now(),
            lastTopVisibleGroup = uuid4(),
            lastSelectedGroup = uuid4(),
        )
        val writer = XmlWriter.write(
            XmlComponent.Meta(
                meta = meta,
                option = XmlOption(Version(4, 1), binaries = mapOf()),
            ),
        )
        val generator = writer.select(XmlTags.META_GENERATOR).text()
        assertEquals(meta.generator, generator)
    }
}
