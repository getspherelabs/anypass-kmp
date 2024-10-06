package io.spherelabs.crypto.kdbx.database.model.component

import io.spherelabs.crypto.kdbx.database.entity.DeletedComponent
import io.spherelabs.crypto.kdbx.database.entity.Group

/**
 * The [KdbxQuery] class is central to retrieving specific entries from the Kdbx database.
 * It provides functionality to filter entries based on custom predicates,and allows for
 * updating or deleting them as necessary. Additionally, it handles the hierarchical nature of groups,
 * enabling structured access to credentials and notes stored in different containers.
 *
 */
data class KdbxQuery(
    val meta: Meta,
    val group: Group,
    val deletedObjects: List<DeletedComponent>,
)
