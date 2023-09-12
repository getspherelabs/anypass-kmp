package io.spherelabs.navigation

import androidx.compose.runtime.Immutable


/**
 * Represents an entry in the back stack.
 *
 * This interface defines the structure of a navigation stack entry, which is used to manage
 * the states of destinations in a navigation system.
 *
 * @param STATE The type of the current state of the destination.
 */
@Immutable
interface NavStackEntry<STATE : Any> {
    /**
     * Gets the current state of the destination.
     */
    val state: STATE

    /**
     * Retrieves or computes a value associated with a given key.
     *
     * @param key The key used to retrieve or compute the value.
     * @param compute A lambda function that computes the value if it doesn't exist.
     * @param onDispose A lambda function that is invoked when the state is popped off the stack
     * for cleanup purposes.
     * @return The computed or retrieved value associated with the key.
     *
     * The computed value will be valid for the lifetime of this destination on the back stack.
     * When the state is removed from the stack, [onDispose] will be invoked to perform cleanup tasks.
     */
    fun <T : Any> getOrPut(key: Any, compute: () -> T, onDispose: (T) -> Unit): T
}
