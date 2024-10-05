package io.spherelabs.crypto.kdbx.database.core.internal

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.core.KdbxDatabase
import io.spherelabs.crypto.kdbx.database.entity.DeletedComponent
import io.spherelabs.crypto.kdbx.database.entity.Entity
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery
import io.spherelabs.crypto.kdbx.database.model.component.Meta



val KdbxDatabase.meta: Meta get() = this.query.meta

val KdbxDatabase.group: Group get() = this.query.group

val KdbxDatabase.deletedObjects: List<DeletedComponent> get() = this.query.deletedObjects

fun KdbxQuery.updateParentGroup(
    block: Group.() -> Group
) = updateWith {
    copy(group = group.modifyGroup(group.id, block))
}
fun KdbxQuery.updateGroup(
    block: Group.() -> Group
) = updateWith{
    copy(group = group.modifyGroup(group.id, block))
}

fun KdbxQuery.updateEntry(
    uuid: Uuid,
    block: Entry.() -> Entry
) = updateWith {
    copy(group = group.modifyEntry(uuid, block))
}


/**
 * Retrieves a list of groups and their corresponding entries that match the given predicate.
 *
 * @param predicate A lambda that defines the condition for filtering entries.
 * @return A list of pairs, where each pair contains a group and a list of its matching entries.
 */
inline fun KdbxQuery.findEntriesInGroups(
    predicate: (Entry) -> Boolean
): List<Pair<Group, List<Entry>>> {
    return group.findChildEntries(false, null, predicate)
}

/**
 * Finds the first entry in the KdbxQuery that matches the given predicate, including entries in the recycle bin.
 *
 * @param predicate A lambda to filter the entry.
 * @return The first matching entry or null if no entry is found.
 */

inline fun KdbxQuery.findFirstEntryIncludingRecycleBin(
    predicate: Entry.() -> Boolean
) : Entry? {
    return group
        .findChildEntry(true, meta.recycleBinUuid, predicate)
        ?.let { (_, entry) -> entry }
}

/**
 * Finds the first entry in the KdbxQuery that matches the given predicate, excluding the recycle bin.
 *
 * @param predicate A lambda to filter the entry.
 * @return The first matching entry or null if no entry is found.
 */
inline fun KdbxQuery.findFirstEntry(
    predicate: Entry.() -> Boolean
): Entry? {
    return group
        .findChildEntry(false, null, predicate)
        ?.let { (_, entry) -> entry }
}
/**
 * Finds the first matching entry and its corresponding group, including entries in the recycle bin.
 *
 * @param predicate A lambda to filter the entry.
 * @return A pair consisting of the group and the matching entry, or null if no entry is found.
 */
inline fun KdbxQuery.findEntryWithGroupIncludingRecycleBin(
    predicate: (Entry) -> Boolean
): Pair<Group,  Entry> ? {
    return this.group.findChildEntry(true, meta.recycleBinUuid, predicate)
}

/**
 * Finds the first matching entry and its corresponding group, excluding the recycle bin.
 *
 * @param predicate A lambda to filter the entry.
 * @return A pair consisting of the group and the matching entry, or null if no entry is found.
 */

inline fun KdbxQuery.findEntryWithGroup(predicate: (Entry) -> Boolean): Pair<Group, Entry>? {
    return this.group.findChildEntry(false, null, predicate)
}

/**
 * Finds a group based on the given predicate. Checks the current group and,
 * if it does not match, searches the child groups recursively.
 *
 * @param predicate A lambda that determines whether a group matches the criteria.
 * @return The group if found, otherwise null.
 */
inline fun KdbxQuery.findGroupBy(
    predicate: (Group) -> Boolean,
): Group? {
    // Check if the current group matches the predicate
    return if (predicate(this.group)) {
            this.group
        } else {
            this.group
                .findChildGroup(null, predicate)
                ?.let { (_, group) -> group }
    }
}

/**
 * Finds a group within the KdbxQuery and returns a pair of the parent group and the found group.
 *
 * @param predicate A lambda that determines if the current group is the one we are looking for.
 * @return A pair containing the parent group and the matching group, or null if no match is found.
 */

inline fun KdbxQuery.findGroup(
    predicate: (Group) -> Boolean,
): Pair<Group?, Group>? {
    return if (predicate(this.group)) {
        null to group
    } else {
        group.findChildGroup(null, predicate)
    }
}

/**
 * Finds an entity in the current group by applying the given block function.
 *
 * @param block A lambda that takes an entity and performs an action with it.
 */

inline fun KdbxQuery.findEntity(
    block: (Entity) -> Unit,
) = group.find(block)

/**
 * Updates the current KdbxQuery by applying the given block function and returning the updated query.
 *
 * @param transformation A lambda that updates the query and returns a modified KdbxQuery object.
 * @return The updated KdbxQuery object.
 */
inline fun KdbxQuery.updateWith(
    crossinline transformation: KdbxQuery.() -> KdbxQuery,
) = transformation(this)




