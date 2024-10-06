package io.spherelabs.crypto.kdbx.database

/**
 * Basic fields which should be added to every entry.
 */
enum class EntryReferenceType(val key: String) {
    Title("Title"),
    UserName("UserName"),
    Password("Password"),
    Url("URL"),
    Notes("Notes");

    operator fun invoke() = this.key

    companion object {
        val keys = entries
            .map(EntryReferenceType::key)
            .toSet()
    }
}
