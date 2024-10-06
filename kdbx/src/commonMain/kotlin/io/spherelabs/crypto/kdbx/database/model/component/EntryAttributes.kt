package io.spherelabs.crypto.kdbx.database.model.component

import io.spherelabs.crypto.kdbx.database.EntryReferenceType


/**
 * Wraps [Map] to override [equals] and take into
 * account order of items in equality checks.
 */
class EntryAttributes(
    private val fields: Map<String, EntryValue>
) : Map<String, EntryValue> by fields {
    val title
        get() = fields[io.spherelabs.crypto.kdbx.database.EntryReferenceType.Title()]
    val userName
        get() = fields[io.spherelabs.crypto.kdbx.database.EntryReferenceType.UserName()]
    val password
        get() = fields[io.spherelabs.crypto.kdbx.database.EntryReferenceType.Password()]
    val url
        get() = fields[io.spherelabs.crypto.kdbx.database.EntryReferenceType.Url()]
    val notes
        get() = fields[io.spherelabs.crypto.kdbx.database.EntryReferenceType.Notes()]

    operator fun get(key: EntryReferenceType): EntryValue? = fields[key()]

    override fun toString(): String {
        return """
             EntryField(
             Title: $title
             Notes: $notes)
        """.trimIndent()
    }

    /**
     * Returns a new [EntryAttributes] with entries having the keys of [fields] and the values
     * obtained by applying the [transform] function to each entry.
     */
    fun mapValues(transform: (Map.Entry<String, EntryValue>) -> EntryValue) =
        EntryAttributes(fields.mapValues(transform))

    /**
     * Returns a new [EntryAttributes] with entries having the keys obtained by applying
     * the [transform] function to each entry and the values of [fields].
     *
     * In case if any two entries are mapped to the equal keys, the value of the latter one
     * will overwrite the value associated with the former one.
     */
    fun mapKeys(transform: (Map.Entry<String, EntryValue>) -> String) =
        EntryAttributes(fields.mapKeys(transform))

    /**
     * Returns [EntryAttributes] containing all key-value pairs with keys matching the given [predicate].
     */
    fun filterKeys(predicate: (String) -> Boolean) =
        EntryAttributes(fields.filterKeys(predicate))

    /**
     * Returns [EntryAttributes] containing all key-value pairs with values matching the given [predicate].
     */
    fun filterValues(predicate: (EntryValue) -> Boolean) =
        EntryAttributes(fields.filterValues(predicate))

    /**
     * Returns a new [EntryAttributes] containing all key-value pairs matching the given [predicate].
     */
    fun filter(predicate: (Map.Entry<String, EntryValue>) -> Boolean) =
        EntryAttributes(fields.filter(predicate))

    /**
     * Returns a new [EntryAttributes] containing all key-value pairs not matching the given [predicate].
     */
    fun filterNot(predicate: (Map.Entry<String, EntryValue>) -> Boolean) =
        EntryAttributes(fields.filterNot(predicate))

    /**
     * Creates a new [EntryAttributes] by replacing or adding an entry from a given key-value [pair].
     * The [pair] is iterated in the end if it has a unique key.
     */
    operator fun plus(pair: Pair<String, EntryValue>) = EntryAttributes(fields + pair)

    /**
     * Creates a new [EntryAttributes] by replacing or adding entries from a given collection of key-value [pairs].
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] collection.
     */
    operator fun plus(pairs: Iterable<Pair<String, EntryValue>>) = EntryAttributes(fields + pairs)

    /**
     * Creates a new [EntryAttributes] by replacing or adding entries from a given array of key-value [pairs].
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] array.
     */
    operator fun plus(pairs: Array<out Pair<String, EntryValue>>) = EntryAttributes(fields + pairs)

    /**
     * Creates a new [EntryAttributes] by replacing or adding entries from a given sequence of key-value [pairs].
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] sequence.
     */
    operator fun plus(pairs: Sequence<Pair<String, EntryValue>>) = EntryAttributes(fields + pairs)

    /**
     * Creates a new [EntryAttributes] by replacing or adding entries from another [map].
     * Those entries of another [map] that are missing in this map are iterated in the end in the order of that [map].
     */
    operator fun plus(map: Map<String, EntryValue>) = EntryAttributes(fields + map)

    /**
     * Returns [EntryAttributes] containing all entries of the original except the entry with the given [key].
     */
    operator fun minus(key: String) = EntryAttributes(fields - key)

    /**
     * Returns [EntryAttributes] containing all entries of the original except those entries
     * the keys of which are contained in the given [keys] collection.
     */
    operator fun minus(keys: Iterable<String>) = EntryAttributes(fields - keys.toSet())

    /**
     * Returns [EntryAttributes] containing all entries of the original except those entries
     * the keys of which are contained in the given [keys] array.
     */
    operator fun minus(keys: Array<String>) = EntryAttributes(fields - keys.toSet())

    /**
     * Returns [EntryAttributes] containing all entries of the original except those entries
     * the keys of which are contained in the given [keys] sequence.
     */
    operator fun minus(keys: Sequence<String>) = EntryAttributes(fields - keys.toSet())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        val iterator = fields.iterator()
        val otherIterator = (other as EntryAttributes).iterator()

        while (true) {
            val diff = iterator.hasNext() xor otherIterator.hasNext()
            if (diff) return false

            if (iterator.hasNext()) {
                if (iterator.next() != otherIterator.next()) {
                    return false
                }
            } else {
                break
            }
        }
        return true
    }

    override fun hashCode(): Int = fields.hashCode()

    companion object {
        /**
         * Returns a new [EntryAttributes] with the specified contents, given as a list
         * of pairs where the first value is the key and the second is the value.
         *
         * If multiple pairs have the same key, the resulting map will contain
         * the value from the last of those pairs.
         *
         * Entries are iterated in the order they were specified.
         */
        fun of(vararg pairs: Pair<String, EntryValue>) = EntryAttributes(mapOf(*pairs))

        /**
         * Creates [EntryAttributes] which is populated with empty [EntryReferenceType]
         * values as required by KeePass contract.
         */
        val Default: EntryAttributes = createDefault()
        private fun createDefault() = EntryAttributes(
            buildMap {
                EntryReferenceType.entries
                    .filter { it != EntryReferenceType.Password }
                    .forEach { field -> put(field(), EntryValue.Plain("")) }

                val password = InternalSecureBytes.fromPlainText("")
                put(io.spherelabs.crypto.kdbx.database.EntryReferenceType.Password(), EntryValue.Encrypted(password))
            }
        )
    }
}
