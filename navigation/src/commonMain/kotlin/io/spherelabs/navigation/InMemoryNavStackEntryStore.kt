package io.spherelabs.navigation

/**
 * An in-memory key-value store for managing backstack state destinations.
 *
 * This [InMemoryNavStackEntryStore] provides a simple in-memory storage mechanism for managing [NavStackEntry] instances
 * associated with specific states. It allows you to retrieve, create, and dispose of entries in the
 * navigation stack.
 *
 * @param STATE The type of the state associated with the backstack entry.
 */
class InMemoryNavStackEntryStore<STATE : Any> {

    /**
     * Retrieves or creates a [NavStackEntry] for the specified [forState].
     *
     */
    @Suppress("UNCHECKED_CAST")
    fun get(forState: STATE): NavStackEntry<STATE> {
        return inMemoryValueStore.getOrPut(forState) {
            NavStackEntryImpl(forState)
        } as NavStackEntry<STATE>
    }

    /**
     * Disposes of the [NavStackEntry] associated with the specified [forState].
     *
     * @param forState The state for which to dispose of the [NavStackEntry].
     */
    fun dispose(forState: STATE) {
        inMemoryValueStore[forState]?.dispose()
        inMemoryValueStore.remove(forState)
    }

    companion object {
        private val inMemoryValueStore = mutableMapOf<Any, NavStackEntryImpl<Any>>()
    }
}