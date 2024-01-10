package io.spherelabs.system.ui.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect

/**
 * Copied from https://github.com/valentinilk/compose-shimmer/tree/master
 */
@Composable
internal expect fun rememberWindowBounds(): Rect
