package io.spherelabs.system.ui.shimmer

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.shimmer(
    customShimmer: Shimmer? = null,
): Modifier = composed(
    factory = {
        val shimmer = customShimmer ?: rememberShimmer(ShimmerBounds.View)

        val width = with(LocalDensity.current) { shimmer.theme.shimmerWidth.toPx() }
        val area = remember(width, shimmer.theme.rotation) {
            ShimmerArea(width, shimmer.theme.rotation)
        }

        LaunchedEffect(area, shimmer) {
            shimmer.boundsFlow.collect {
                area.updateBounds(it)
            }
        }

        remember(area, shimmer) { ShimmerModifier(area, shimmer.effect) }
    },
    inspectorInfo = debugInspectorInfo {
        name = "shimmer"
        properties["customShimmer"] = customShimmer
    },
)

internal class ShimmerModifier(
    private val area: ShimmerArea,
    private val effect: ShimmerEffect,
) : DrawModifier, OnGloballyPositionedModifier {

    override fun ContentDrawScope.draw() {
        with(effect) { draw(area) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        val viewBounds = coordinates.unclippedBoundsInWindow()
        area.viewBounds = viewBounds
    }
}

/**
 * The regular [boundsInWindow] returns a rect that is clipped by the edges of the window. So if
 * a view is scrolled half way out of the screen, the rect returned would only have half the size
 * required for the shimmer calculations.
 *
 * @return Rect that is not clipped by the window's size.
 */
fun LayoutCoordinates.unclippedBoundsInWindow(): Rect {
    return try {
        // Can throw an IllegalStateException on some devices
        val positionInWindow = positionInWindow()
        Rect(
            positionInWindow.x,
            positionInWindow.y,
            positionInWindow.x + size.width,
            positionInWindow.y + size.height,
        )
    } catch (_: IllegalStateException) {
        Rect.Zero
    }
}
