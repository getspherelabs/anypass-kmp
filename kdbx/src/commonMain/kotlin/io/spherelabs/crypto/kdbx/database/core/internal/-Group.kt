package io.spherelabs.crypto.kdbx.database.core.internal

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.Stack
import io.spherelabs.crypto.kdbx.database.entity.Entity
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import kotlinx.datetime.Clock


internal typealias ChildEntries =  List<Pair<Group, List<Entry>>>
internal typealias SingleEntry =  Pair<Group, Entry>?
/**
 * Finds and returns a list of entries in this group and its subgroups that match the given predicate.
 * The search traverses through the group hierarchy and returns pairs of groups and the corresponding entries
 * that satisfy the search condition.
 *
 * @param useGroupOverride Boolean flag indicating whether to respect the group's search override flag.
 * @param recycleBinUuid Optional UUID of the recycle bin group. If provided, the recycle bin group is excluded from the search.
 * @param predicate A lambda function that defines the condition for filtering entries.
 * @return A list of pairs where each pair contains a group and a list of matching entries.
 */

inline fun Group.findChildEntries(
    useGroupOverride: Boolean = false,
    recycleBinUuid: Uuid? = null,
    predicate: (Entry) -> Boolean
): ChildEntries {

    val result = mutableListOf<Pair<Group, List<Entry>>>()
    val stack = Stack<Group>()
    stack.push(this)

    while (stack.isNotEmpty) {
        val current = stack.pop()

        val isSearchable = current.isSearchable

        if (!useGroupOverride || isSearchable) {
            val found = current.entries.filter(predicate)

            if (found.isNotEmpty()) {
                result.add(current to found)
            }
        }

        current.childGroups
            .filterNot {
                it.id == recycleBinUuid
            }
            .forEach { stack.push(it) }
    }

    return result
}


/**
 * Finds a single entry in this group or its subgroups that matches the given predicate.
 * The search will traverse through the group hierarchy, returning the first matching entry found.
 *
 * @param useGroupOverride A flag to determine whether to respect the group's `isSearchable` property.
 *                         If `false`, all groups will be searched regardless of their `isSearchable` status.
 *                         Default is `false`.
 * @param recycleBinUuid The UUID of the recycle bin group. Groups with this UUID will be excluded from the search.
 *                       Default is `null`, meaning no groups are excluded.
 * @param predicate A function that evaluates an `Entry`. Returns `true` if the entry satisfies the search condition.
 *
 * @return A pair of the `Group` containing the found `Entry`, and the `Entry` itself,
 *         or `null` if no matching entry is found.
 */

inline fun Group.findChildEntry(
    useGroupOverride: Boolean = false,
    recycleBinUuid: Uuid? = null,
    predicate: (Entry) -> Boolean
): SingleEntry {
    val stack = Stack<Group>()
    stack.push(this)

    while (stack.isNotEmpty) {
        val current  = stack.pop()

        val isSearchable = current.isSearchable

        if (!useGroupOverride || isSearchable) {
            for (entry in current.entries) {
                if (predicate(entry)) {
                    return current to entry
                }
            }
        }

        current.childGroups
            .filterNot { it.id == recycleBinUuid}
            .forEach { stack.push(it) }
    }

    return null
}

/**
 * Updates a group identified by the specified [uuid].
 * If the group matches the provided [uuid], the specified [block] is applied to modify the group.
 *
 * @param uuid The UUID of the group to be updated.
 * @param block A lambda that takes a [Group] and returns an updated [Group].
 * @return A new [Group] with the updated properties.
 */

fun Group.updateGroup(
    uuid: Uuid,
    block: Group.() -> Group
): Group {
    return if (this.id == uuid) {
        block(this).copy(
            lastModifiedAt = Clock.System.now(),
        )
    } else {
        copy(childGroups = childGroups.map { group: Group -> group.updateGroup(uuid, block) })
    }
}
/**
 * Updates an entry with the specified [id] within the group or any of its child groups.
 * If the entry is found, the provided [block] is applied to modify the entry.
 *
 * @param id The UUID of the entry to be updated.
 * @param block A lambda that takes an [Entry] and returns an updated [Entry].
 * @return A new [Group] with the updated entry.
 */

 fun Group.updateEntry(
    id: Uuid,
    block: Entry.() -> Entry
): Group {
    val item = entries.find { it.id == id }

    // If the entry is found, update it and return a new group with the modified entry
    if (item != null) {
        val modifiedEntry = block(item).copy(
            lastModifiedAt = Clock.System.now()
        )
        return copy(entries = entries - item + modifiedEntry)
    }

    // If the entry is not found, recursively search and update in child groups
    return copy(childGroups = childGroups.map { it.updateEntry(id, block) })
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

/**
 * Traverses the current group and its child groups, applying the provided [block] to each entity.
 * This includes the group itself, all its entries, and all descendant groups.
 *
 * @param block A lambda function that takes an [Entity] as input and performs an action on it.
 */
inline fun Group.find(
    block: (Entity) -> Unit,
) {
    val stack = Stack<Group>().apply { push(this@find) }

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
