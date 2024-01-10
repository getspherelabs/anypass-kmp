package io.spherelabs.system.ui.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect

/**
 * Copied from https://github.com/valentinilk/compose-shimmer/tree/master
 */
@Composable
internal fun rememberShimmerBounds(
    shimmerBounds: ShimmerBounds,
): Rect? {
    return when (shimmerBounds) {
        ShimmerBounds.Custom -> Rect.Zero
        ShimmerBounds.View -> null
        ShimmerBounds.Window -> rememberWindowBounds()
    }
}

sealed interface ShimmerBounds {
    data object Custom : ShimmerBounds
    data object View : ShimmerBounds
    data object Window : ShimmerBounds
}
