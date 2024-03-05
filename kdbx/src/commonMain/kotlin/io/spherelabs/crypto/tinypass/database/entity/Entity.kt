package io.spherelabs.crypto.tinypass.database.entity

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.model.component.PredefinedIcon
import kotlinx.datetime.Instant

/**
 * A [Entity] represents the base actual data for Kdbx database.
 */
sealed interface Entity {
    val id: Uuid
    val createdAt: Instant?
    val lastModifiedAt: Instant?
    val expired: Boolean
    val expiredAt: Instant?
    val icon: PredefinedIcon
    val customIconUuid: Uuid?
    val tags: List<String>
}
