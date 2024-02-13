package io.spherelabs.crypto.tinypass.database.model.component

data class DatabaseContent(
    val meta: Meta,
    val group: Group,
    val deletedObjects: List<DeletedComponent>,
)
