package io.spherelabs.designsystem.hooks

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun <T> useState(defaultValue: T): Pair<T, (T) -> Unit> {
    val (state, setState) = remember { mutableStateOf(defaultValue) }

    return Pair(state, setState)
}

@Composable
fun useScope(): CoroutineScope {
    return rememberCoroutineScope()
}

@Composable
fun useSnackbar(): SnackbarHostState {
    return remember { SnackbarHostState() }
}

@Composable
fun useEffect(vararg keys: Any, block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(keys = keys, block = block)
}

@Composable
fun useEffect(key1: Any, block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(key1 = key1, block = block)
}

@Composable
fun useTransition(defaultValue: Boolean): MutableTransitionState<Boolean> {
    return remember { MutableTransitionState(defaultValue) }
}

@Composable
fun <T> useUpdatedState(newValue: T): State<T> = remember {
    mutableStateOf(newValue)
}.apply { value = newValue }

@Composable
fun useScroll(initialValue: Int = 0): ScrollState {
    return rememberScrollState(initialValue)
}