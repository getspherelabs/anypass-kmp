package io.spherelabs.crypto.kdbx.database.entity

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.common.Default
import io.spherelabs.crypto.kdbx.database.model.component.EntryFields
import io.spherelabs.crypto.kdbx.database.model.component.PredefinedIcon
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


data class Group(
    override val id: Uuid,
    override val expiredAt: Instant? = null,
    override val expired: Boolean = false,
    override val createdAt: Instant = Instant.Default,
    override val lastModifiedAt: Instant = Instant.Default,
    override val tags: List<String> = listOf(),
    override val icon: PredefinedIcon = PredefinedIcon.Folder,
    override val customIconUuid: Uuid? = null,
    val name: String,
    val notes: String = "",
    val isSearchable: Boolean = false,
    val isAutoTyped: Boolean = false,
    val isExpanded: Boolean = true,
    val typeSequence: String? = null,
    val lastTopVisibleEntryId: Uuid? = null,
    val previousParentGroupId: Uuid? = null,
    val childGroups: List<Group> = emptyList(),
    val entries: List<Entry> = emptyList(),
    val customData: Map<String, CustomDataValue> = mapOf(),
) : Entity


internal class MutableGroup(
    var id: Uuid,
    var expiredAt: Instant? = null,
    var expired: Boolean = false,
    var createdAt: Instant = Clock.System.now(),
    var lastModifiedAt: Instant = Clock.System.now(),
    var icon: PredefinedIcon = PredefinedIcon.Key,
    var tags: MutableList<String> = mutableListOf(),
    var customIconUuid: Uuid? = null,
    var name: String = "",
    var notes: String = "",
    var isSearchable: Boolean = false,
    var isAutoTyped: Boolean = false,
    var isExpanded: Boolean = true,
    var typeSequence: String? = null,
    var lastTopVisibleEntryId: Uuid? = null,
    var previousParentGroupId: Uuid? = null,
    var childGroups: MutableList<Group> = mutableListOf(),
    var entries: MutableList<Entry> = mutableListOf(),
    var customData: MutableMap<String, CustomDataValue> = mutableMapOf()
)

internal inline fun buildGroup(
    uuid: Uuid,
    crossinline block: MutableGroup.() -> Unit
): Group = MutableGroup(uuid)
    .apply(block)
    .run {
        Group(
           id = uuid,
            name = name,
            notes = notes,
            icon = icon,
            expiredAt = expiredAt,
            expired = expired,
            createdAt = createdAt,
            lastModifiedAt = lastModifiedAt,
            customIconUuid = customIconUuid,
            isSearchable = isSearchable,
            isAutoTyped = isAutoTyped,
            isExpanded = isExpanded,
            typeSequence = typeSequence,
            lastTopVisibleEntryId = lastTopVisibleEntryId,
            previousParentGroupId = previousParentGroupId,
            tags = tags,
            childGroups = childGroups,
            entries = entries,
            customData = customData
        )
    }
