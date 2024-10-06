package io.spherelabs.crypto.kdbx.database.entity

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.kdbx.database.common.Default
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
class GroupBuilder(private val id: Uuid = uuid4()) {
    private var expiredAt: Instant? = null
    private var expired: Boolean = false
    private var createdAt: Instant = Clock.System.now()
    private var lastModifiedAt: Instant = Clock.System.now()
    private var icon: PredefinedIcon = PredefinedIcon.Folder
    private var tags: List<String> = emptyList() // Changed to immutable List
    private var customIconUuid: Uuid? = null
    private var name: String = ""
    private var notes: String = ""
    private var isSearchable: Boolean = false
    private var isAutoTyped: Boolean = false
    private var isExpanded: Boolean = true
    private var typeSequence: String? = null
    private var lastTopVisibleEntryId: Uuid? = null
    private var previousParentGroupId: Uuid? = null
    private var childGroups: List<Group> = emptyList()
    private var entries: List<Entry> = emptyList()
    private var customData: Map<String, CustomDataValue> = emptyMap()

    fun expiredAt(expiredAt: Instant?) = apply { this.expiredAt = expiredAt }
    fun expired(expired: Boolean) = apply { this.expired = expired }
    fun createdAt(createdAt: Instant) = apply { this.createdAt = createdAt }
    fun lastModifiedAt(lastModifiedAt: Instant) = apply { this.lastModifiedAt = lastModifiedAt }
    fun icon(icon: PredefinedIcon) = apply { this.icon = icon }
    fun tags(tags: List<String>) = apply { this.tags = tags }
    fun customIconUuid(customIconUuid: Uuid?) = apply { this.customIconUuid = customIconUuid }
    fun name(name: String) = apply { this.name = name }
    fun notes(notes: String) = apply { this.notes = notes }
    fun searchable(isSearchable: Boolean) = apply { this.isSearchable = isSearchable }
    fun autoTyped(isAutoTyped: Boolean) = apply { this.isAutoTyped = isAutoTyped }
    fun expanded(isExpanded: Boolean) = apply { this.isExpanded = isExpanded }
    fun typeSequence(typeSequence: String?) = apply { this.typeSequence = typeSequence }
    fun lastTopVisibleEntryId(lastTopVisibleEntryId: Uuid?) = apply { this.lastTopVisibleEntryId = lastTopVisibleEntryId }
    fun previousParentGroupId(previousParentGroupId: Uuid?) = apply { this.previousParentGroupId = previousParentGroupId }

    fun addChildGroup(group: Group) = apply { this.childGroups += group }
    fun addEntries(entries: List<Entry>) = apply { this.entries = entries }

    fun customData(customData: Map<String, CustomDataValue>) = apply { this.customData = customData }

    private fun validate() {
        require(name.isNotEmpty()) { "Group name must not be empty." }
    }

    fun build(): Group {
        validate()

        return Group(
            id = id,
            expiredAt = expiredAt,
            expired = expired,
            createdAt = createdAt,
            lastModifiedAt = lastModifiedAt,
            tags = tags,
            icon = icon,
            customIconUuid = customIconUuid,
            name = name,
            notes = notes,
            isSearchable = isSearchable,
            isAutoTyped = isAutoTyped,
            isExpanded = isExpanded,
            typeSequence = typeSequence,
            lastTopVisibleEntryId = lastTopVisibleEntryId,
            previousParentGroupId = previousParentGroupId,
            childGroups = childGroups,
            entries = entries,
            customData = customData
        )
    }
}

fun buildGroup(id: Uuid = uuid4(), init: GroupBuilder.() -> Unit): Group {
    return GroupBuilder(id).apply(init).build()
}
