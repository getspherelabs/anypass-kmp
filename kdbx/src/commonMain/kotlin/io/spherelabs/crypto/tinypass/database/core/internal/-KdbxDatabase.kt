package io.spherelabs.crypto.tinypass.database.core.internal

import io.spherelabs.crypto.tinypass.database.Stack
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.entity.DeletedComponent
import io.spherelabs.crypto.tinypass.database.entity.Entity
import io.spherelabs.crypto.tinypass.database.entity.Entry
import io.spherelabs.crypto.tinypass.database.entity.Group
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil

val KdbxDatabase.meta: Meta get() = this.query.meta

val KdbxDatabase.group: Group get() = this.query.group

val KdbxDatabase.deletedObjects: List<DeletedComponent> get() = this.query.deletedObjects

inline fun KdbxQuery.updateWith(
    crossinline block: KdbxQuery.() -> KdbxQuery,
) = block(this)

inline fun KdbxQuery.find(
    block: (Entity) -> Unit,
) = group.find(block)

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

private fun Entry.deleteHistory(
    meta: Meta,
): Entry {
    val now = Clock.System.now()

    return copy(
        history = history.filter {
            if (it.lastModifiedAt != null) {
                val days = now.daysUntil(it.lastModifiedAt, TimeZone.UTC)
                days < meta.maintenanceHistoryDays
            } else {
                true
            }
        }.takeLast(meta.historyMaxItems),
    )
}
