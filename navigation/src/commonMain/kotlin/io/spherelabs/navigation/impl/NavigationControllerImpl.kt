package io.spherelabs.navigation.impl

import io.spherelabs.navigation.LockerStack
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.NavigationEvents
import io.spherelabs.navigation.stack.NavigationStack
import io.spherelabs.navigation.stack.Stack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Default implementation of [NavigationController]
 */
internal class NavigationControllerImpl<STATE : Any>(
    private val coroutineScope: CoroutineScope = MainScope(),
    private val stack: Stack<STATE> = NavigationStack(),
) : NavigationController<STATE> {

    private val _events = MutableSharedFlow<NavigationEvents<STATE>>()
    override val events = _events.asSharedFlow()

    override val currentState: STATE? get() = stack.top

    override val backStackSize: Int get() = stack.size

    val backStack get() = stack.items

    private val mutex = Mutex()

    override fun navigateTo(newState: STATE) {
        coroutineScope.launch {
            mutex.withLock {
                val top = stack.top
                stack.push(newState)
                val nextEvent = if (top == null) {
                    NavigationEvents.InitialState(newState)
                } else {
                    NavigationEvents.OnNavigateTo(newState)
                }
                _events.emit(nextEvent)
            }
        }
    }

    override fun navigateUp() {
        coroutineScope.launch {
            mutex.withLock {
                val poppedState = stack.pop()
                val nextState = stack.top
                val nextEvent = if (nextState == null) {
                    NavigationEvents.OnStackEmpty(poppedState)
                } else {
                    NavigationEvents.OnPopUp(nextState, poppedState)
                }
                _events.emit(nextEvent)
            }
        }
    }

    override fun navigateUp(newState: STATE) {
        coroutineScope.launch {
            mutex.withLock {
                val poppedState = stack.pop()
                val nextState = stack.top

                val nextEvent = if (nextState == null) {
                    NavigationEvents.OnStackEmpty(poppedState)
                } else {
                    NavigationEvents.OnPopUp(newState, poppedState)
                }
                _events.emit(nextEvent)
            }
        }
    }
}
