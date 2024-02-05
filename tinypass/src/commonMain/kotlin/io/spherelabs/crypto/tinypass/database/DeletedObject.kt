package io.spherelabs.crypto.tinypass.database

import com.benasher44.uuid.Uuid
import kotlinx.datetime.Instant

data class DeletedObject(
    val id: Uuid,
    val deletionTime: Instant
)
