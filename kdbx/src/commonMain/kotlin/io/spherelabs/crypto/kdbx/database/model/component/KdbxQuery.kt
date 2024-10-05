package io.spherelabs.crypto.kdbx.database.model.component

import io.spherelabs.crypto.kdbx.database.entity.DeletedComponent
import io.spherelabs.crypto.kdbx.database.entity.Group

/**
 * The [KdbxQuery] is key to retrieving specific entries from the database,
 * filtering them based on custom predicates, and updating or deleting them when necessary.
 * It also handles the hierarchical nature of groups, providing structured access to credentials and notes stored in different containers.
 */
data class KdbxQuery(
    val meta: Meta,
    val group: Group,
    val deletedObjects: List<DeletedComponent>,
)
