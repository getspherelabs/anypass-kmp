package io.spherelabs.crypto.kdbx.database.xml

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.parser.Parser
import io.spherelabs.crypto.kdbx.database.EncryptionSaltGenerator
import io.spherelabs.crypto.kdbx.database.common.readBoolean
import io.spherelabs.crypto.kdbx.database.common.readByteString
import io.spherelabs.crypto.kdbx.database.common.readFirst
import io.spherelabs.crypto.kdbx.database.common.readInstant
import io.spherelabs.crypto.kdbx.database.common.readInt
import io.spherelabs.crypto.kdbx.database.common.readString
import io.spherelabs.crypto.kdbx.database.common.readUuid
import io.spherelabs.crypto.kdbx.database.entity.CustomDataValue
import io.spherelabs.crypto.kdbx.database.entity.CustomIcon
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import io.spherelabs.crypto.kdbx.database.entity.MutableEntry
import io.spherelabs.crypto.kdbx.database.entity.buildGroup
import io.spherelabs.crypto.kdbx.database.header.CrsAlgorithm
import io.spherelabs.crypto.kdbx.database.model.component.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import okio.BufferedSource
import okio.ByteString


object XmlReader {
    fun read(source: BufferedSource, option: XmlOption): KdbxQuery {
        val document = source.readByteArray().decodeToString()

        val documentation = Ksoup.parse(
            document,
            baseUri = "",
            parser = Parser.xmlParser(),
        )

        val meta = readMeta(documentation)
        val group = readGroup(documentation)


        println("Group = $group")
        return KdbxQuery(
            meta = meta,
            group = group,
            deletedObjects = emptyList(),
        )
    }

    private fun readGroup(element: Element): Group = with(element) {
        val tag = getElementsByTag(XmlTags.GROUP_TAG_NAME)
        val id = tag.first()?.children()?.first()?.readUuid(XmlTags.UUID) ?: uuid4()

        return buildGroup(id) {
            tag.first()?.children()?.forEach { childElement ->
                when (childElement.tagName()) {
                    XmlTags.GROUP_NAME -> {
                        println("Group name = ${childElement.text()}")
                        name = childElement.text()
                    }

                    XmlTags.GROUP_NOTES -> {
                        notes = childElement.text()
                        println("Tags = $notes")
                    }

                    XmlTags.ENTRY_TAG_NAME ->
                        entries.add(readEntry(childElement))

                }

            }
        }

    }

    private fun readMeta(element: Element): Meta = with(element) {
        val generator = readString(XmlTags.META_GENERATOR)
        val headerHash = readByteString(XmlTags.META_HEADER_HASH)
        val settingsChanged = readInstant(XmlTags.META_SETTINGS_CHANGED)
        val name = readString(XmlTags.META_DATABASE_NAME)
        val nameChanged = readInstant(XmlTags.META_DATABASE_NAME_CHANGED)
        val description = readString(XmlTags.META_DATABASE_DESCRIPTION)
        val descriptionChanged = readInstant(XmlTags.META_DATABASE_DESCRIPTION_CHANGED)
        val defaultUser = readString(XmlTags.META_DEFAULT_USER_NAME)
        val defaultUserChanged = readInstant(XmlTags.META_DEFAULT_USER_NAME_CHANGED)
        val maintenanceHistoryDays = readString(XmlTags.META_MAINTENANCE_HISTORY_DAYS).let { days ->
            if (days.isEmpty()) 0 else days.toInt()
        }
        val color = readString(XmlTags.META_COLOR)
        val masterKeyChanged = readInstant(XmlTags.META_MASTER_KEY_CHANGED)
        val masterKeyChangeRec = readString(XmlTags.META_MASTER_KEY_CHANGED_RECORD).let { record ->
            if (record.isEmpty()) 0 else record.toInt()
        }
        val masterKeyChangeForce = readString(XmlTags.META_MASTER_KEY_CHANGE_FORCE).let { record ->
            if (record.isEmpty()) 0 else record.toInt()
        }
        val recycleBinEnabled = readBoolean(XmlTags.META_RECYCLE_BIN_ENABLED)
        val recycleBinChanged = readInstant(XmlTags.META_RECYCLE_BIN_CHANGED)
        val recycleBinUuid = readUuid(XmlTags.META_RECYCLE_BIN_UUID)
        val entryTemplatesGroup = readUuid(XmlTags.META_ENTRY_TEMPLATES_GROUP)
        val entryTemplatesGroupChanged = readInstant(XmlTags.META_ENTRY_TEMPLATES_GROUP_CHANGED)
        val historyMaxItems = readString(XmlTags.META_HISTORY_MAX_ITEMS).let { item ->
            if (item.isEmpty()) 0 else item.toInt()
        }
        val historyMaxSize = readString(XmlTags.META_HISTORY_MAX_SIZE).let { size ->
            if (size.isEmpty()) 0 else size.toInt()
        }
        val lastSelectedGroup = readUuid(XmlTags.META_LAST_SELECTION_GROUP)
        val lastTopVisibleGroup = readUuid(XmlTags.META_LAST_TOP_VISIBLE_GROUP)
        val memoryProtection = readFirst(XmlTags.MEMORY_PROTECTION_TAG_NAME)?.let {
            readMemoryProtection(it)
        } ?: setOf()
        val binaries = select(XmlTags.BINARY_TAG_NAME).first()?.let {
            readBinaries(it)
        } ?: linkedMapOf()
        val customIcon = select(XmlTags.ICON_TAG_NAME).first()?.let {
            readCustomIcon(it)
        } ?: mapOf()
        val customData = select(XmlTags.CUSTOM_DATA_TAG_NAME).first()?.let {
            readCustomData(it)
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

    @OptIn(ExperimentalEncodingApi::class)
    private fun readCustomIcon(element: Element): Map<Uuid, CustomIcon> = with(element) {
        val tag = getElementsByTag(XmlTags.ICON_TAG_NAME)


        return if (tag.first()?.readUuid(XmlTags.ICON_UUID) != null) {
            tag.associate {

                val id = it.readUuid(XmlTags.ICON_UUID)
                val data = Base64.decode(it.select(XmlTags.ICON_DATA).text())
                val name = it.select(XmlTags.ICON_NAME).text()
                val lastModified = it.select(XmlTags.LAST_MODIFICATION_TIME).readInstant()


                checkNotNull(id)

                id to CustomIcon(data = data, name, lastModified)
            }
        } else {
            mapOf()
        }
    }

    private fun readCustomData(element: Element): Map<String, CustomDataValue> {
        val tag = element.getElementsByTag(XmlTags.CUSTOM_DATA_ITEM)

        return tag.associate { item ->
            val key = item.select(XmlTags.CUSTOM_DATA_KEY).text()
            val value = item.select(XmlTags.CUSTOM_DATA_VALUE).text()

            key to CustomDataValue(value)
        }
    }

    private fun readMemoryProtection(element: Element): Set<MemoryProtectionFlag> {
        val tag = element.getElementsByTag(XmlTags.MEMORY_PROTECTION_TAG_NAME)

        return buildSet {
            if (tag.select(XmlTags.MEMORY_PROTECTION_NAME).hasText()) {
                add(MemoryProtectionFlag.Title)
            }
            if (tag.select(XmlTags.MEMORY_PROTECTION_USERNAME).hasText()) {
                add(MemoryProtectionFlag.UserName)
            }
            if (tag.select(XmlTags.MEMORY_PROTECTION_PASSWORD).hasText()) {
                add(MemoryProtectionFlag.Password)
            }
            if (tag.select(XmlTags.MEMORY_PROTECTION_URL).hasText()) {
                add(MemoryProtectionFlag.Url)
            }
            if (tag.select(XmlTags.MEMORY_PROTECTION_NOTES).hasText()) {
                add(MemoryProtectionFlag.Notes)
            }
        }
    }

    private fun readBinaries(element: Element): Map<ByteString, BinaryData> {
        val binary = element.tagName(XmlTags.BINARY_DATA_BINARY, namespace = Parser.NamespaceXml)
        return binary.let { attr ->
            val result = readBinaryData(attr)
            mutableMapOf(result.second.hash to result.second)
        }
    }

    private fun readEntries(element: Element): List<Entry> {
        return element.children().filter { it.tagName() == XmlTags.ENTRY_TAG_NAME }.map {
            readEntry(it)
        }
    }

    private fun readEntry(element: Element): Entry = with(element) {
        val untitledFields = mutableListOf<EntryValue>()
        val id = element.readUuid(XmlTags.UUID) ?: uuid4()

        return buildEntry(id) {
            element.children().forEach { element ->
                icon = element.readInt(XmlTags.ENTRY_ICON_ID).let(PredefinedIcon.entries::getOrNull)
                    ?: PredefinedIcon.Key
                customIconUuid = element.readUuid(XmlTags.ENTRY_CUSTOM_ICON_ID)
                foregroundColor = element.readString(XmlTags.ENTRY_FOREGROUND_COLOR)
                backgroundColor = element.readString(XmlTags.ENTRY_BACKGROUND_COLOR)
                overrideUrl = element.readString(XmlTags.ENTRY_OVERRIDE_URL)
                if (element.tagName() == XmlTags.ENTRY_FIELDS_TAG_NAME) {
                    val (name, value) = readField(element)

                    if (name != null) {
                        fields[name] = value
                    } else {
                        untitledFields += value
                    }
                }
                element.readString(XmlTags.ENTRY_TAGS).split(",").forEach(tags::add)
            }
            println("Fields = ${fields.toList()}")

            if (untitledFields.isNotEmpty()) {
                recoverUntitledFields(untitledFields)
            }
        }
    }

    /**
     * Recovers up to [UInt.MAX_VALUE] untitled fields.
     */
    private fun MutableEntry.recoverUntitledFields(
        untitledFields: List<EntryValue>,
    ) {
        for (value in untitledFields) {
            var n = 1U
            var name = "Untitled"

            while (name in fields) {
                name = "Untitled ($n)"
                n++

                if (n == UInt.MAX_VALUE) return
            }
            fields[name] = value
        }
    }


    internal inline fun buildEntry(
        uuid: Uuid,
        crossinline block: MutableEntry.() -> Unit,
    ): Entry = MutableEntry(uuid)
        .apply(block)
        .run {
            Entry(
                id = uuid,
                icon = icon,
                customIconUuid = customIconUuid,
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
                overrideUrl = overrideUrl,
                autoType = autoType,
                fields = EntryFields(fields),
                tags = tags,
                binaries = binaries,
                history = history,
                customData = customData,
                previousParentGroup = previousParentGroup,
                qualityCheck = qualityCheck,
            )
        }


    private fun readField(element: Element): Pair<String?, EntryValue> {
        val key = element.readString(XmlTags.ENTRY_FIELDS_ITEM_KEY)
        val protected = element.readBoolean(XmlTags.ENTRY_FIELDS_ITEM_VALUE)

        return if (protected) {
            val bytes = element.readByteString(XmlTags.ENTRY_FIELDS_ITEM_VALUE)

            val salt =
                EncryptionSaltGenerator.create(CrsAlgorithm.ChaCha20, bytes ?: ByteString.EMPTY)
                    .getSalt(bytes?.size ?: 0)

            key to EntryValue.Encrypted(InternalSecureBytes.from(salt))
        } else {
            val text = element.readString(XmlTags.ENTRY_FIELDS_ITEM_VALUE)
            key to EntryValue.Plain(text)
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun readBinaryData(element: Element): Pair<Int, BinaryData> {
        val id = checkNotNull(element.attribute(XmlTags.BINARY_DATA_ID)?.value).toInt()
        val bytes = Base64.decode(element.text())
        val compressed =
            checkNotNull(element.attribute(XmlTags.BINARY_DATA_COMPRESSED)?.value).toBoolean()
        val binary = when {
            compressed -> BinaryData.Compressed(rawContent = bytes, false)
            else -> BinaryData.Uncompressed(rawContent = bytes, memoryProtection = false)
        }
        return id to binary
    }
}
