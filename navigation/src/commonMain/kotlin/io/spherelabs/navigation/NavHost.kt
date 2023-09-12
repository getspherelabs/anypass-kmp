package io.spherelabs.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import io.spherelabs.navigation.impl.NavHostScopeImpl
import io.spherelabs.navigation.impl.rememberNavHostScope
import kotlinx.coroutines.flow.onSubscription


/**
 * Constructs a navigation graph within the current composition.
 *
 * @param navigationController The navigation controller responsible for managing the navigation flow.
 * @param initialState The initial state representing the starting destination in the navigation graph.
 * @param block The lambda function for defining the structure of the navigation graph.
 */
@Composable
fun <STATE : Any> NavHost(
    navigationController: NavigationController<STATE>,
    initialState: STATE,
    block: NavHostScope<STATE>.() -> Unit,
) {
    val hostScope = rememberNavHostScope(navigationController, block)

    hostScope.NavHostImpl(
        navigationController = navigationController,
        initialState = initialState,
    )
}


@Composable
private fun <STATE : Any> NavHostScopeImpl<STATE>.NavHostImpl(
    navigationController: NavigationController<STATE>,
    initialState: STATE,
) {
    val state by rememberNavState(
        navigationController = navigationController,
        initialState = initialState,
    )

    AnimatedContent(
        targetState = state,
        transitionSpec = {
            when (targetState?.animation) {
                NavigationAnimation.NAVIGATING -> {
                    slideInHorizontally { height -> height } + fadeIn() togetherWith
                            slideOutHorizontally { height -> -height } + fadeOut()
                }
                NavigationAnimation.POPPING_UP -> {
                    slideInHorizontally { height -> -height } + fadeIn() togetherWith
                            slideOutHorizontally { height -> height } + fadeOut()
                }
                else -> {
                    fadeIn() togetherWith fadeOut()
                }
            }.using(SizeTransform(clip = false))
        },
    ) { nextState ->
        val navState = nextState?.state
        if (navState != null) {
            this@NavHostImpl.compose(navState)
        }
    }

    BackHandler(true) { navigationController.navigateUp() }

    val appFinisher = LocalAppFinisher.current

    LaunchedEffect(state) {
        if (state?.animation === NavigationAnimation.EXIT) {
            appFinisher.finish()
        }
    }
}

@Composable
private fun <STATE : Any> rememberNavState(
    navigationController: NavigationController<STATE>,
    initialState: STATE,
): State<NavState<STATE>?> {

    val initialValue = navigationController.currentState?.let {
        NavState(it, NavigationAnimation.NO_ANIMATION)
    }

    return produceState(
        key1 = navigationController,
        key2 = initialState,
        initialValue = initialValue,
    ) {
        navigationController.events
            .onSubscription {
                if (navigationController.backStackSize == 0) {
                    navigationController.navigateTo(initialState)
                }
            }.collect { event ->
                val animation = when (event) {
                    is NavigationEvents.InitialState -> NavigationAnimation.NO_ANIMATION
                    is NavigationEvents.OnNavigateTo -> NavigationAnimation.NAVIGATING
                    is NavigationEvents.OnPopUp -> NavigationAnimation.POPPING_UP
                    is NavigationEvents.OnStackEmpty -> NavigationAnimation.EXIT
                }
                value = NavState(event.state, animation)
            }
    }
}

/**
 * Navigation state with animation details for navigation
 */
@Immutable
data class NavState<STATE : Any>(
    val state: STATE?,
    val animation: NavigationAnimation,
)