package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.Stack
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Group(
    override val id: Uuid,
    override val expiredAt: Instant? = null,
    override val expired: Boolean = false,
    override val createdAt: Instant = Clock.System.now(),
    override val lastModifiedAt: Instant = Clock.System.now(),
    override val tags: List<String> = listOf(),
    val title: String,
    val notes: String = "",
    val isSearchable: Boolean = false,
    val isAutoTyped: Boolean = false,
    val isExpanded: Boolean = true,
    val typeSequence: String? = null,
    val lastTopVisibleEntryId: Uuid? = null,
    val previousParentGroupId: Uuid? = null,
    val childGroups: List<Group> = emptyList(),
    val customData: Map<String, CustomDataValue> = mapOf(),
) : Entity {

    fun search(
        action: (Entity) -> Unit,
    ) {
        val bucket = Stack<Group>()
        bucket.push(this)

        while (bucket.isNotEmpty) {
            val currentGroup = bucket.pop()
            action(currentGroup as Group)

            currentGroup.childGroups.forEach { group ->
                bucket.push(group)
            }
        }
    }


}

