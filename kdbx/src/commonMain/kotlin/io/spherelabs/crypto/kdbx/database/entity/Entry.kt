package io.spherelabs.crypto.kdbx.database.entity

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.entity.autotype.AutoType
import io.spherelabs.crypto.kdbx.database.model.component.EntryAttributes
import io.spherelabs.crypto.kdbx.database.model.component.EntryValue
import io.spherelabs.crypto.kdbx.database.model.component.PredefinedIcon
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Entry(
    override val id: Uuid,
    override val expiredAt: Instant? = null,
    override val expired: Boolean = false,
    override val createdAt: Instant = Clock.System.now(),
    override val lastModifiedAt: Instant = Clock.System.now(),
    override val icon: PredefinedIcon = PredefinedIcon.Key,
    override val customIconUuid: Uuid? = null,
    override val tags: List<String> = listOf(),
    val foregroundColor: String? = null,
    val backgroundColor: String? = null,
    val overrideUrl: String = "",
    val binaries: List<BinaryReference> = listOf(),
    val autoType: AutoType? = null,
    val fields: EntryAttributes = EntryAttributes.Default,
    val history: List<Entry> = listOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
    val previousParentGroup: Uuid? = null,
    val qualityCheck: Boolean = true,
) : Entity


/**
 * Represents a mutable version of an Entry, used to construct or modify entries before converting
 * them into immutable Entry instances. This class holds various properties related to the entry,
 * such as its UUID, icon, color, fields, tags, and history.
 */
internal class MutableEntry(
    var uuid: Uuid,
    var expiredAt: Instant? = null,
    var expired: Boolean = false,
    var createdAt: Instant = Clock.System.now(),
    var lastModifiedAt: Instant = Clock.System.now(),
    var icon: PredefinedIcon = PredefinedIcon.Key,
    var customIconUuid: Uuid? = null,
    var foregroundColor: String? = null,
    var backgroundColor: String? = null,
    var overrideUrl: String = "",
    var autoType: AutoType? = null,
    var previousParentGroup: Uuid? = null,
    var qualityCheck: Boolean = true,
    var fields: MutableMap<String, EntryValue> = mutableMapOf(),
    var tags: MutableList<String> = mutableListOf(),
    var binaries: MutableList<BinaryReference> = mutableListOf(),
    var history: MutableList<Entry> = mutableListOf(),
    var customData: MutableMap<String, CustomDataValue> = mutableMapOf(),
)

/**
 * Builds an immutable Entry from a MutableEntry by applying the provided modifications
 * via the passed block. This function allows for easy customization of entries during creation.
 *
 * @param uuid The unique identifier for the entry.
 * @param block A lambda that allows custom modifications on the MutableEntry object.
 * @return A fully constructed, immutable Entry object.
 */
internal inline fun createEntry(
    uuid: Uuid,
    crossinline block: MutableEntry.() -> Unit,
): Entry = MutableEntry(uuid)
    .apply(block) // Apply customizations to MutableEntry
    .toImmutableEntry() // Convert to immutable Entry

/**
 * Converts the current MutableEntry instance into an immutable Entry.
 *
 * This method finalizes the mutable structure of the entry and constructs an immutable Entry object,
 * which can be used throughout the application without risk of unintentional modifications.
 *
 * @return A new Entry instance with the properties of the current MutableEntry.
 */
internal fun MutableEntry.toImmutableEntry(): Entry = Entry(
    id = uuid,
    icon = icon,
    expired = expired,
    expiredAt = expiredAt,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    customIconUuid = customIconUuid,
    foregroundColor = foregroundColor,
    backgroundColor = backgroundColor,
    overrideUrl = overrideUrl,
    autoType = autoType,
    fields = EntryAttributes(fields), // Wrapping mutable fields in an immutable wrapper
    tags = tags.toList(), // Ensure tags are immutable
    binaries = binaries.toList(), // Ensure binaries are immutable
    history = history.toList(), // Ensure history is immutable
    customData = customData.toMap(), // Ensure custom data is immutable
    previousParentGroup = previousParentGroup,
    qualityCheck = qualityCheck,
)
