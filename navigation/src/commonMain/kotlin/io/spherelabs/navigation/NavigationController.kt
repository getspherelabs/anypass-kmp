package io.spherelabs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import io.spherelabs.navigation.impl.NavigationControllerImpl
import io.spherelabs.navigation.stack.NavigationStack
import kotlinx.coroutines.flow.SharedFlow

/**
 * NavController manages app navigation within a [NavHost].
 */
interface NavigationController<STATE> {
    /**
     * Count of items in the backstack
     */
    val backStackSize: Int

    /**
     * The current destination state in the navigation graph
     */
    val currentState: STATE?

    /**
     * Navigate to a [newState] in the current Navigation graph
     */
    fun navigateTo(newState: STATE)

    /**
     * Pops the current destination from Navigation graph
     */
    fun navigateUp()

    fun navigateUp(newState: STATE)

    /**
     * Reactive stream for [NavigationEvents]
     */
    val events: SharedFlow<NavigationEvents<STATE>>
}

@Immutable
sealed interface NavigationEvents<STATE> {
    val state: STATE?

    @Immutable
    data class InitialState<STATE>(override val state: STATE) : NavigationEvents<STATE>

    @Immutable
    data class OnNavigateTo<STATE>(override val state: STATE) : NavigationEvents<STATE>

    @Immutable
    data class OnPopUp<STATE>(
        override val state: STATE,
        val poppedState: STATE,
    ) : NavigationEvents<STATE>

    @Immutable
    class OnStackEmpty<STATE>(val previousState: STATE) : NavigationEvents<STATE> {
        override val state: STATE? = null

        override fun equals(other: Any?): Boolean {
            return other is OnStackEmpty<*>
        }

        override fun hashCode(): Int {
            return this::class.hashCode()
        }
    }
}

/**
 * Remembers the navigation controller in this composition scope.
 * Across process-death or screen configuration change, compose will lose state. In order to
 * preserve states, [onSave] will be used to save the current state and [onRestore] will be used
 * to re-construct the state to maintain the previous state of navigation.
 */
@Composable
fun <STATE : Any, SAVABLE> rememberNavigationController(
    onSave: (STATE) -> SAVABLE,
    onRestore: (SAVABLE) -> STATE,
): NavigationController<STATE> {
    return rememberSaveable(
        Unit,
        saver = listSaver(
            save = { it.backStack.map { state -> onSave(state) } },
            restore = { savables ->
                NavigationControllerImpl(stack = NavigationStack(savables.map { onRestore(it) }))
            },
        ),
    ) { NavigationControllerImpl() }
}
