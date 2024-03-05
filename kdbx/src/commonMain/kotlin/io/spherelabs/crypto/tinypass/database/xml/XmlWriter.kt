package io.spherelabs.crypto.tinypass.database.xml

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.*
import io.spherelabs.crypto.tinypass.database.common.writeBytes
import io.spherelabs.crypto.tinypass.database.common.writeUuid
import io.spherelabs.crypto.tinypass.database.common.deserialize
import io.spherelabs.crypto.tinypass.database.entity.CustomDataValue
import io.spherelabs.crypto.tinypass.database.entity.CustomIcon
import io.spherelabs.crypto.tinypass.database.entity.Group
import io.spherelabs.crypto.tinypass.database.model.autotype.toXmlString
import io.spherelabs.crypto.tinypass.database.model.component.*
import okio.ByteString

object XmlWriter {

    fun write(query: KdbxQuery, option: XmlOption): String {
        val version = "2.0"
        val encoding = "utf-8"

        val document = xml(version, encoding) {
            writeMeta(query.meta, option).appendTo(this)
            appendElement("root").appendTo(this).apply {
                writeGroup(query.group).appendTo(this)
            }
        }

        return document.toString()
    }


    private fun writeGroup(group: Group): Element = with(group) {
        return element(XmlTags.GROUP_TAG_NAME) {
            writeElement(XmlTags.UUID, uuid = id)
            writeElement(XmlTags.GROUP_NAME, name)
            writeElement(XmlTags.GROUP_NOTES, notes)
            writeElement(XmlTags.GROUP_IS_EXPANDED, expired)
            writeElement(XmlTags.GROUP_ENABLE_AUTO_TYPE, isAutoTyped)
            writeElement(XmlTags.GROUP_ENABLE_SEARCHING, isSearchable)
            lastTopVisibleEntryId?.let { uuid ->
                writeElement(XmlTags.GROUP_LAST_TOP_VISIBLE_ENTRY, uuid)
            }
            addChildren(writeCustomData(customData))
        }
    }

    private fun writeCustomData(
        data: Map<String, CustomDataValue>,
    ): Element {
        return element(XmlTags.CUSTOM_DATA_TAG_NAME) {
            for ((key, item) in data) {
                appendElement(XmlTags.CUSTOM_DATA_ITEM).apply {
                    writeElement(XmlTags.CUSTOM_DATA_KEY, key)
                    writeElement(XmlTags.CUSTOM_DATA_VALUE, item.value)
                }
            }
        }
    }

    private fun writeMeta(meta: Meta, option: XmlOption): Element = with(meta) {
        return element(XmlTags.META_TAG_NAME) {
            writeElement(XmlTags.META_GENERATOR, generator)
            headerHash?.takeIf { option.kdbxVersion.major < 4 }?.let { bytes ->
                writeElement(XmlTags.META_HEADER_HASH, bytes.toByteArray())
            }
            settingsChanged?.takeIf { option.kdbxVersion.major >= 4 }?.let { instant ->
                writeElement(XmlTags.META_SETTINGS_CHANGED, instant.deserialize(option))
            }
            writeElement(XmlTags.META_DATABASE_NAME, name)
            appendElement(FormatXml.Tags.Meta.DatabaseNameChanged).text(
                checkNotNull(
                    nameChanged?.deserialize(
                        option,
                    ),
                ),
            )
            appendElement(FormatXml.Tags.Meta.DatabaseDescription).text(description)
            appendElement(FormatXml.Tags.Meta.DatabaseDescriptionChanged).text(
                checkNotNull(
                    descriptionChanged?.deserialize(
                        option,
                    ),
                ),
            )
            appendElement(FormatXml.Tags.Meta.DefaultUserName).text(
                defaultUser,
            )
            appendElement(FormatXml.Tags.Meta.DefaultUserNameChanged).text(
                checkNotNull(defaultUserChanged?.deserialize(option)),
            )
            appendElement(FormatXml.Tags.Meta.MaintenanceHistoryDays).text(
                maintenanceHistoryDays.toString(),
            )
            appendElement(FormatXml.Tags.Meta.Color).text(
                color ?: "",
            )
            appendElement(FormatXml.Tags.Meta.MasterKeyChanged).writeInstant(
                masterKeyChanged,
                option,
            )
            appendElement(FormatXml.Tags.Meta.MasterKeyChangeRec).text(
                masterKeyChangeRec.toString(),
            )
            appendElement(FormatXml.Tags.Meta.MasterKeyChangeForce).text(
                masterKeyChangeForce.toString(),
            )
            appendElement(FormatXml.Tags.Meta.RecycleBinEnabled).text(
                recycleBinEnabled.toXmlString(),
            )
            appendElement(FormatXml.Tags.Meta.RecycleBinUuid).writeUuid(recycleBinUuid)
            appendElement(FormatXml.Tags.Meta.RecycleBinChanged).writeInstant(
                recycleBinChanged,
                option,
            )
            appendElement(FormatXml.Tags.Meta.EntryTemplatesGroup).writeUuid(entryTemplatesGroup)
            appendElement(FormatXml.Tags.Meta.EntryTemplatesGroupChanged).writeInstant(
                entryTemplatesGroupChanged,
                option,
            )
            appendElement(FormatXml.Tags.Meta.HistoryMaxItems).text(historyMaxItems.toString())
            appendElement(FormatXml.Tags.Meta.HistoryMaxSize).text(historyMaxSize.toString())
            appendElement(FormatXml.Tags.Meta.LastSelectedGroup).writeUuid(lastSelectedGroup)
            appendElement(FormatXml.Tags.Meta.LastTopVisibleGroup).writeUuid(lastTopVisibleGroup)
            addChildren(writeMemoryProtection(memoryProtection))
            addChildren(writeCustomIcon(option, customIcons))
            addChildren(writeCustomData(customData))
            var binaryCount = 0
            binaries.values.forEach { binary ->
                addChildren(binary.serialize(binaryCount))
                binaryCount++
            }
        }
    }

    private fun writeCustomIcon(context: XmlOption, customIcon: Map<Uuid, CustomIcon>): Element {
        return Element(XmlTags.ICON_TAG_NAME).apply {
            for ((key, item) in customIcon) {
                appendElement(XmlTags.ICON_ITEM).apply {
                    appendElement(XmlTags.ICON_UUID).writeUuid(key)
                    appendElement(XmlTags.ICON_DATA).writeBytes(item.data)

                    if (context.kdbxVersion.isAtLeast(4, 1)) {
                        appendElement(XmlTags.ICON_NAME).text(item.name.toString())
                        appendElement(XmlTags.LAST_MODIFICATION_TIME).text(
                            checkNotNull(item.lastModified?.deserialize(context)),
                        )
                    }
                }
            }

        }
    }

    private fun writeBinaryData(binary: Pair<ByteString, BinaryData>): Element {
        return Element(XmlTags.BINARY_DATA_BINARY).apply {

        }
    }
    private fun writeMemoryProtection(
        memoryProtection: Set<MemoryProtectionFlag>,
    ): Element {
        return Element(XmlTags.MEMORY_PROTECTION_TAG_NAME).apply {
            MemoryProtectionFlag.values().forEach { flag ->
                appendElement(flag.value).text(memoryProtection.contains(flag).toXmlString())
            }
        }
    }

    private fun element(tagName: String, builder: Element.() -> Unit): Element =
        Element(tagName).apply(builder)

    private inline fun Element.writeElement(
        tagName: String,
        value: String,
        option: XmlOption,
    ) {
        appendElement(tagName).text(value)
    }

    private inline fun Element.writeElement(
        tagName: String,
        value: String,
    ) {
        appendElement(tagName).text(value)
    }

    private inline fun Element.writeElement(
        tagName: String,
        raw: ByteArray,
    ) {
        appendElement(tagName).writeBytes(raw)
    }

    private inline fun Element.writeElement(
        tagName: String,
        raw: Boolean,
    ) {
        appendElement(tagName).text(raw.toXmlString())
    }

    private inline fun Element.writeElement(
        tagName: String,
        uuid: Uuid,
    ) {
        appendElement(tagName).writeUuid(uuid)
    }

    private inline fun Element.writeIfElement(
        predicate: Boolean,
        tagName: String,
        raw: String,
    ) {
        if (predicate) appendElement(tagName).text(raw)
    }

}
