package io.spherelabs.crypto.kdbx.database.model.component


/**
 * Wraps value fields stored in [Entry].
 */
sealed class EntryValue {
    /**
     * Returns underlying value, decrypting if required.
     */
    abstract val content: String

    /**
     * Checks whether [content] is empty without exposing [InternalSecureBytes].
     */
    abstract fun isEmpty(): Boolean

    /**
     * Should be used for non-sensitive values.
     */
    data class Plain(
        override val content: String,
    ) : EntryValue() {
        override fun isEmpty() = content.isEmpty()
    }

    /**
     * Should be used for secrets.
     */
    data class Encrypted(
        private val value: InternalSecureBytes,
    ) : EntryValue() {
        override val content: String get() = value.plainText

        override fun isEmpty() = value.size == 0
    }

    /**
     * Replaces wrapped value with result of the [block].
     */
    inline fun map(block: (String) -> String) = when (this) {
        is Plain -> Plain(block(content))
        is Encrypted -> Encrypted(InternalSecureBytes.fromPlainText(block(content)))
    }
}
