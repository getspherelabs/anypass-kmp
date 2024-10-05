package io.spherelabs.crypto.tinypass.database.entity

import com.benasher44.uuid.Uuid
import kotlinx.datetime.Instant

data class DeletedComponent(
    val id: Uuid?,
    val timestamp: Instant,
)
