package io.spherelabs.crypto.tinypass.database.model.component

import io.spherelabs.crypto.tinypass.database.entity.DeletedComponent
import io.spherelabs.crypto.tinypass.database.entity.Group

data class KdbxQuery(
    val meta: Meta,
    val group: Group,
    val deletedObjects: List<DeletedComponent>,
)
