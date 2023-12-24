package io.spherelabs.designsystem.hooks

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope

@Composable
inline fun <T> useState(defaultValue: T): Pair<T, (T) -> Unit> {
  val (state, setState) = remember { mutableStateOf(defaultValue) }

  return Pair(state, setState)
}

@Composable
inline fun useIntState(defaultValue: Int): Pair<Int, (Int) -> Unit> {
  val (state, setState) = remember { mutableIntStateOf(defaultValue) }

  return Pair(state, setState)
}

@Composable
inline fun useBooleanState(defaultValue: Boolean): Pair<Boolean, (Boolean) -> Unit> {
  val (state, setState) = remember { mutableStateOf(defaultValue) }

  return Pair(state, setState)
}

@Composable
inline fun useScope(): CoroutineScope {
  return rememberCoroutineScope()
}

@Composable
inline fun useSnackbar(): SnackbarHostState {
  return remember { SnackbarHostState() }
}

@Composable
inline fun useEffect(vararg keys: Any, noinline block: suspend CoroutineScope.() -> Unit) {
  LaunchedEffect(keys = keys, block = block)
}

@Composable
inline fun useEffect(key1: Any, noinline block: suspend CoroutineScope.() -> Unit) {
  LaunchedEffect(key1 = key1, block = block)
}

@Composable
inline fun useTransition(defaultValue: Boolean): MutableTransitionState<Boolean> {
  return remember { MutableTransitionState(defaultValue) }
}

@Composable
inline fun <T> useUpdatedState(newValue: T): State<T> =
    remember { mutableStateOf(newValue) }.apply { value = newValue }

@Composable
fun useScroll(initialValue: Int = 0): ScrollState {
  return rememberScrollState(initialValue)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun usePagerEffect(state: PagerState, action: (Int) -> Unit) {
  LaunchedEffect(state) { snapshotFlow { state.currentPage }.collect { action.invoke(it) } }
}
