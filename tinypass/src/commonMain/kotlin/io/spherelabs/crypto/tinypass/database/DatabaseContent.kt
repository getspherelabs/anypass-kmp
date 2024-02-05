package io.spherelabs.crypto.tinypass.database

data class DatabaseContent(
    val meta: Meta,
    val group: Group,
    val deletedObjects: List<DeletedObject>
)

