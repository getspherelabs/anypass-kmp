package io.spherelabs.crypto.tinypass.database

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class TimeData(
    val creationTime: Instant?,
    val lastAccessTime: Instant?,
    val lastModificationTime: Instant?,
    val locationChanged: Instant?,
    val expiryTime: Instant?,
    val expires: Boolean = false,
    val usageCount: Int = 0
) {
    companion object {
        fun create() = requireNotNull(Clock.System.now())
            .let { now ->
                TimeData(
                    creationTime = now,
                    lastAccessTime = now,
                    lastModificationTime = now,
                    locationChanged = now,
                    expiryTime = null,
                    expires = false,
                    usageCount = 0
                )
            }
    }
}

