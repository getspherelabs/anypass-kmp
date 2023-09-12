package io.spherelabs.navigation

import androidx.compose.runtime.mutableStateMapOf

/**
 * Default implementation of [NavStackEntry]
 */
internal data class NavStackEntryImpl<STATE : Any>(override val state: STATE) : NavStackEntry<STATE> {
    private val store = mutableStateMapOf<Any, StoreValueHolder<*>>()
    private var isDisposed = false

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getOrPut(key: Any, compute: () -> T, onDispose: (T) -> Unit): T {
        if (isDisposed) {
            error("The NavStackEntry(state=$state) is already disposed")
        }
        val currentValue = store[key] as? StoreValueHolder<T>
        if (currentValue != null) {
            return currentValue.apply { onCleared = onDispose }.value
        }
        val valueHolder = StoreValueHolder(compute(), onDispose)
        store[key] = valueHolder
        return valueHolder.value
    }

    /**
     * Disposes all values associated of a [state]
     */
    internal fun dispose() {
        isDisposed = true
        store.values.forEach { holder -> holder.dispose() }
        store.clear()
    }

    /**
     * Value and its disposer holder
     */
    private class StoreValueHolder<T : Any>(val value: T, var onCleared: (T) -> Unit) {
        fun dispose() {
            onCleared(value)
        }
    }
}