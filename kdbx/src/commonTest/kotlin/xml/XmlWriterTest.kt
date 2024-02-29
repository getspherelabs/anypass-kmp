package xml

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import kotlin.test.Test
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

//        val writer = XmlWriter.write(
//            option = XmlOption(KdbxVersion(4, 1), binaries = mapOf()),
//            XmlComponent.Meta(
//                meta = meta,
//
//            ),
//        )
//        val generator = writer.select(XmlTags.META_GENERATOR).text()
//        assertEquals(meta.generator, generator)
    }
}