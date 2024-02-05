package io.spherelabs.crypto.tinypass.database

/**
 * Basic fields which should be added to every entry.
 */
enum class BasicField(val key: String) {
    Title("Title"),
    UserName("UserName"),
    Password("Password"),
    Url("URL"),
    Notes("Notes");

    operator fun invoke() = this.key

    companion object {
        val keys = values()
            .map(BasicField::key)
            .toSet()
    }
}
