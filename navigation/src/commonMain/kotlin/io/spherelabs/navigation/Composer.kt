package io.spherelabs.navigation

import androidx.compose.runtime.Composable


/**
 * A composer for destinations in the navigation graph.
 *
 * [Composer] defines a composer for composing destinations in a navigation graph. The [STATE]
 * type parameter represents the specific state associated with the destination, and [BASE] represents
 * a supertype of the [STATE].
 *
 * @param STATE The specific state type associated with the destination.
 * @param BASE A supertype of [STATE].
 */
fun interface Composer<STATE : BASE, BASE : Any> {
    /**
     * Composes a destination using the provided [entry].
     *
     * @param entry The [NavStackEntry] representing the destination's state.
     */
    @Composable
    fun compose(entry: NavStackEntry<STATE>)
}

/**
 * Creates a composer for destinations in the navigation graph.
 *
 * This [Composer] function creates and returns a [Composer] instance using the provided composable [block].
 * The [STATE] type parameter represents the specific state associated with the destination, and [BASE]
 * represents a supertype of the [STATE].
 *
 * @param STATE The specific state type associated with the destination.
 * @param BASE A supertype of [STATE].
 * @param block A composable function that defines how to compose the destination using a [NavStackEntry].
 * @return A [Composer] instance.
 */
fun <STATE : BASE, BASE : Any> Composer(
    block: @Composable (NavStackEntry<STATE>) -> Unit,
): Composer<STATE, BASE> {
    return ComposerImpl(block)
}

/**
 * Default implementation of the [Composer] interface.
 *
 * This [ComposerImpl] provides a default implementation of the [Composer] interface, allowing you to define
 * how to compose a destination using a composable [block].
 *
 * @param STATE The specific state type associated with the destination.
 * @param BASE A supertype of [STATE].
 * @param block A composable function that defines how to compose the destination using a [NavStackEntry].
 */
class ComposerImpl<STATE : BASE, BASE : Any>(
    val block: @Composable (NavStackEntry<STATE>) -> Unit,
) : Composer<STATE, BASE> {
    @Composable
    override fun compose(entry: NavStackEntry<STATE>) {
        block(entry)
    }
}
