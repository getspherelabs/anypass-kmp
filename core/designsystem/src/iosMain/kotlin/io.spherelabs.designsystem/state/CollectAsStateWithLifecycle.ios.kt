package io.spherelabs.designsystem.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow

@Composable actual fun <T> StateFlow<T>.collectAsStateWithLifecycle(): State<T> = collectAsState()
