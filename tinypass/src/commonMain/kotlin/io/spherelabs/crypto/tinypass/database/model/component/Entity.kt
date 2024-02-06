package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import kotlinx.datetime.Instant

sealed interface Entity {
    val id: Uuid
    val createdAt: Instant?
    val lastModifiedAt: Instant?
    val expired: Boolean
    val expiredAt: Instant?
    val tags: List<String>
}
