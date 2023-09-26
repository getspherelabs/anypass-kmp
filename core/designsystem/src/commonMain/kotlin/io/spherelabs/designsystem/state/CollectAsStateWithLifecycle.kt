package io.spherelabs.designsystem.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow

@Composable expect fun <T> StateFlow<T>.collectAsStateWithLifecycle(): State<T>
