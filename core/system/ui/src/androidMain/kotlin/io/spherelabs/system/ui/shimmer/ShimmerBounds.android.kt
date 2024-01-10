package io.spherelabs.system.ui.shimmer

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect

/**
 * Copied from https://github.com/valentinilk/compose-shimmer/tree/master
 */

@Composable
internal actual fun rememberWindowBounds(): Rect = remember {
    val metrics = Resources.getSystem().displayMetrics

    Rect(0f, 0f, metrics.widthPixels.toFloat(), metrics.heightPixels.toFloat())
}
