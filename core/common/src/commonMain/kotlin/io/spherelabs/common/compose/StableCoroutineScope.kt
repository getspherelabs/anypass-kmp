package io.spherelabs.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberStableCoroutineScope(): StableCoroutineScope {
    val scope = rememberCoroutineScope()
    return remember { StableCoroutineScope(scope) }
}

@Stable
class StableCoroutineScope(scope: CoroutineScope) : CoroutineScope by scope