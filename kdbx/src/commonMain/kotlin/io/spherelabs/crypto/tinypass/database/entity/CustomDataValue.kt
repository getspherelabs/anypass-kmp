package io.spherelabs.crypto.tinypass.database.entity

import kotlinx.datetime.Instant

/**
 * Arbitrary string data holder for database/group/entry metadata.
 */
data class CustomDataValue(
    val value: String,
    val lastModified: Instant? = null,
)
