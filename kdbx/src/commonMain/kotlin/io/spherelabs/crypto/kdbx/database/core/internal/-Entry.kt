package io.spherelabs.crypto.kdbx.database.core.internal

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.kdbx.database.Stack
import io.spherelabs.crypto.kdbx.database.entity.Entry
import io.spherelabs.crypto.kdbx.database.entity.Group
import io.spherelabs.crypto.kdbx.database.model.component.Meta
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil



private fun Entry.deleteHistory(
    meta: Meta,
): Entry {
    val now = Clock.System.now()

    return copy(
        history = history.filter {
            run {
                val days = now.daysUntil(it.lastModifiedAt, TimeZone.UTC)
                days < meta.maintenanceHistoryDays
            }
        }.takeLast(meta.historyMaxItems),
    )
}
