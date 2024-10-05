package io.spherelabs.crypto.tinypass.database.entity

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.common.Default
import io.spherelabs.crypto.tinypass.database.model.component.EntryFields
import io.spherelabs.crypto.tinypass.database.model.component.PredefinedIcon
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
    val fields: EntryFields = EntryFields.Default,
    val childGroups: List<Group> = emptyList(),
    val entries: List<Entry> = emptyList(),
    val customData: Map<String, CustomDataValue> = mapOf(),
) : Entity
