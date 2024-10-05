package io.spherelabs.crypto.kdbx.database.core.internal

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.Stack
import io.spherelabs.crypto.kdbx.database.entity.Entity
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import kotlinx.datetime.Clock

inline fun Group.findChildEntries(
    useGroupOverride: Boolean = false,
    recycleBinUuid: Uuid? = null,
    predicate: (Entry) -> Boolean
): List<Pair<Group, List<Entry>>> {
    val result = mutableListOf<Pair<Group, List<Entry>>>()
    val stack = Stack<Pair<Group, Boolean>>()
    stack.push(this to true)

    while (stack.isNotEmpty) {
        val (current, parentSearchEnabled) = stack.pop()

        val isSearchable = current.isSearchable


        if (!useGroupOverride || isSearchable) {
            val found = current.entries.filter { predicate(it) }

            if (found.isNotEmpty()) {
                result.add(current to found)
            }
        }

        current.childGroups
            .filter { recycleBinUuid == null || it.id.compareTo(recycleBinUuid) != 0 }
            .forEach { stack.push(it to isSearchable) }
    }

    return result
}
inline fun Group.findChildEntry(
    useGroupOverride: Boolean = false,
    recycleBinUuid: Uuid? = null,
    predicate: (Entry) -> Boolean
): Pair<Group, Entry>? {
    val stack = Stack<Pair<Group, Boolean>>()
    stack.push(this to true)

    while (stack.isNotEmpty) {
        val (current, parentSearchEnabled) = stack.pop()

        val isSearchable = current.isSearchable

        if (!useGroupOverride || isSearchable) {
            for (entry in current.entries) {
                if (predicate(entry)) {
                    return current to entry
                }
            }
        }

        current.childGroups
            .filter { recycleBinUuid == null || it.id.compareTo(recycleBinUuid) != 0 }
            .forEach { stack.push(it to isSearchable) }
    }

    return null
}

fun Group.modifyGroup(
    uuid: Uuid,
    block: Group.() -> Group
): Group {
    return if (this.id == uuid) {
        val now = Clock.System.now()
        block(this).copy(
            lastModifiedAt = now,
        )
    } else {
        println("Update group = $block")
        copy(childGroups = childGroups.map { group: Group -> group.modifyGroup(uuid, block) })
    }
}

 fun Group.modifyEntry(
    id: Uuid,
    block: Entry.() -> Entry
): Group {
    val item = entries.find { entry -> entry.id == id}

    return if (item != null) {
        val now = Clock.System.now()
        val modifiedEntry = block(item).copy(
            lastModifiedAt = item.lastModifiedAt,
        )
        copy(entries = (entries - item) + modifiedEntry)
    } else {
        copy(childGroups =childGroups.map { it.modifyEntry(id, block) })
    }
}

inline fun Group.findChildGroup(
    recycleBinUuid: Uuid? = null,
    predicate: (Group) -> Boolean
): Pair<Group, Group>? {
    val stack = Stack<Pair<Group, Group>>()
    childGroups
        .filter { recycleBinUuid == null || it.id.compareTo(recycleBinUuid) != 0 }
        .forEach { stack.push(this to it) }

    while (stack.isNotEmpty) {
        val (parent, current) = stack.pop()

        if (predicate(current)) {
            return parent to current
        }
        current.childGroups
            .filter { recycleBinUuid == null || it.id.compareTo(recycleBinUuid) != 0 }
            .forEach { stack.push(current to it) }
    }

    return null
}

inline fun Group.find(
    block: (Entity) -> Unit,
) {
    val stack = Stack<Group>()
    stack.push(this)

    while (stack.isNotEmpty) {
        val current = stack.pop()
        block(current)

        for (entry in current.entries) {
            block(entry)
        }
        for (group in current.childGroups) {
            stack.push(group)
        }
    }
}
