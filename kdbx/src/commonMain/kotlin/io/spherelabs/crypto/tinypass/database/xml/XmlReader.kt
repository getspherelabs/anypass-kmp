package io.spherelabs.crypto.tinypass.database.xml

import com.benasher44.uuid.uuid4
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.parser.Parser
import com.fleeksoft.ksoup.ported.BufferReader
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.getInstant
import io.spherelabs.crypto.tinypass.database.common.selectAsUuid
import io.spherelabs.crypto.tinypass.database.model.component.*
import io.spherelabs.crypto.tinypass.database.model.component.CustomIcons
import okio.BufferedSource
import okio.ByteString.Companion.decodeBase64


object XmlReader {
    fun read(source: BufferedSource, option: XmlOption): KdbxQuery {
        val documentation = Ksoup.parse(
            bufferReader = BufferReader(source),
            baseUri = "",
            charsetName = null,
            parser = Parser.xmlParser(),
        )

        val root = documentation.select(XmlTags.ROOT)
        val meta = readMeta(documentation)

        return KdbxQuery(
            meta = meta,
            group = Group(
                id = uuid4(),
                title = "test.kdbx",
                isAutoTyped = true,
                isSearchable = true,
            ),
            deletedObjects = emptyList(),)
    }

    private fun readMeta(element: Element): Meta = with(element) {
        val generator = select(Meta.Generator).text()
        val headerHash = select(Meta.HeaderHash).text().decodeBase64()
        val settingsChanged = selectFirst(Meta.SettingsChanged)?.getInstant()
        val name = select(Meta.DatabaseName).text()
        val nameChanged = selectFirst(Meta.DatabaseNameChanged)?.getInstant()
        val description = select(Meta.DatabaseDescription).text()
        val descriptionChanged = selectFirst(Meta.DatabaseDescriptionChanged)?.getInstant()
        val defaultUser = select(Meta.DefaultUserName).text()
        val defaultUserChanged = selectFirst(Meta.DefaultUserNameChanged)?.getInstant()
        val maintenanceHistoryDays = select(Meta.MaintenanceHistoryDays).text().let { days ->
            if (days.isEmpty()) 0 else days.toInt()
        }
        val color = element.select(Meta.Color).text()
        val masterKeyChanged = selectFirst(Meta.MasterKeyChanged)?.getInstant()
        val masterKeyChangeRec = element.select(Meta.MasterKeyChangeRec).text().let {
            if (it.isEmpty()) 0 else it.toInt()
        }
        val masterKeyChangeForce = element.select(Meta.MasterKeyChangeForce).text().let {
            if (it.isEmpty()) 0 else it.toInt()
        }
        val recycleBinEnabled = select(Meta.RecycleBinEnabled).text().toBoolean()
        val recycleBinChanged = selectFirst(Meta.RecycleBinChanged)?.getInstant()
        val recycleBinUuid = selectAsUuid(Meta.RecycleBinUuid)
        val entryTemplatesGroup = selectAsUuid(Meta.EntryTemplatesGroup)
        val entryTemplatesGroupChanged = selectFirst(Meta.EntryTemplatesGroupChanged)?.getInstant()
        val historyMaxItems = select(Meta.HistoryMaxItems).text().let{
            if (it.isEmpty()) 0 else it.toInt()
        }
        val historyMaxSize = select(Meta.HistoryMaxSize).text().let {
            if (it.isEmpty()) 0 else it.toInt()
        }
        val lastSelectedGroup = selectAsUuid(Meta.LastSelectedGroup)
        val lastTopVisibleGroup = selectAsUuid(Meta.LastTopVisibleGroup)
        val memoryProtection = select(MemoryProtectionFlag.TAG_NAME).first()?.let {
            MemoryProtectionFlag.deserialize(it)
        } ?: setOf()
        val binaries = select(FormatXml.Tags.Meta.Binaries.TagName).first()?.let {
            BinaryData.deserializeElements(it)
        } ?: linkedMapOf()
        val customIcon = select(FormatXml.Tags.Meta.CustomIcons.TagName).first()?.let {
            CustomIcons.deserialize(it)
        } ?: mapOf()
        val customData = select(FormatXml.Tags.CustomData.TagName).first()?.let {
            CustomDataValue.deserialize(it)
        } ?: mapOf()

        return Meta(
            generator = generator,
            headerHash = headerHash,
            settingsChanged = settingsChanged,
            name = name,
            nameChanged = nameChanged,
            description = description,
            descriptionChanged = descriptionChanged,
            defaultUser = defaultUser,
            defaultUserChanged = defaultUserChanged,
            maintenanceHistoryDays = maintenanceHistoryDays,
            color = color,
            masterKeyChanged = masterKeyChanged,
            masterKeyChangeRec = masterKeyChangeRec,
            masterKeyChangeForce = masterKeyChangeForce,
            recycleBinEnabled = recycleBinEnabled,
            recycleBinUuid = recycleBinUuid,
            recycleBinChanged = recycleBinChanged,
            entryTemplatesGroup = entryTemplatesGroup,
            entryTemplatesGroupChanged = entryTemplatesGroupChanged,
            historyMaxItems = historyMaxItems,
            historyMaxSize = historyMaxSize,
            lastSelectedGroup = lastSelectedGroup,
            lastTopVisibleGroup = lastTopVisibleGroup,
            memoryProtection = memoryProtection,
            binaries = binaries,
            customIcons = customIcon,
            customData = customData,
        )
    }

//    private fun readGroup(element: Element): Group {
//
//    }
//
//    private fun read
}
