package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.ThresholdConfig
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.haroncode.lazycardstack.LazyCardStackState
import com.haroncode.lazycardstack.rememberLazyCardStackState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun LazyCardStack(
    modifier: Modifier = Modifier,
    threshold: (Orientation) -> ThresholdConfig = { FractionalThreshold(0.3f) },
    velocityThreshold: Dp = SwipeableDefaults.VelocityThreshold,
    directions: Set<SwipeDirection> = setOf(SwipeDirection.Left, SwipeDirection.Right),
    state: LazyCardStackState = rememberLazyCardStackState(),
    onSwipedItem: (Int, SwipeDirection) -> Unit = { _, _ -> },
    content: LazyCardStackScope.() -> Unit
) {
    val itemProviderLambda = rememberLazyCardStackItemProviderLambda(state, content)
    val measurePolicy = rememberLazyCardStackMeasurePolicy(state, itemProviderLambda)

    val scope = rememberCoroutineScope()
    LazyLayout(
        modifier = Modifier
            .then(state.remeasurementModifier)
            .then(state.awaitLayoutModifier)
            .swiper(
                state = state.swiperState,
                threshold = threshold,
                directions = directions,
                velocityThreshold = velocityThreshold,
                onSwiped = { direction ->
                    val currentIndex = state.visibleItemIndex
                    scope.launch {
                        state.snapTo(currentIndex + 1)
                        onSwipedItem(currentIndex, direction)
                    }
                }
            )
            .then(modifier),
        itemProvider = itemProviderLambda(),
        measurePolicy = measurePolicy
    )
}

class LazyCardMeasuredItem(
    val key: Any,
    private val relativeIndex: Int,
    private val dragOffset: IntOffset,
    private val scale: Float,
    private val rotation: Float,
    private val placeables: List<Placeable>
) {

    fun place(scope: Placeable.PlacementScope) = with(scope) {
        placeables.forEach { placeable ->
            if (relativeIndex == 0) {
                val isDragEnabled =
                    (placeable.parentData as? DragableEnabledParentData)?.isEnabled ?: true

                val offset = if (isDragEnabled) dragOffset else IntOffset.Zero
                val rotation = if (isDragEnabled) rotation else 0.0f

                placeable.placeRelativeWithLayer(offset, zIndex = 1.0f) { rotationZ = rotation }
            } else {
                placeable.placeRelativeWithLayer(IntOffset.Zero, zIndex = -1.0f) {
                    scaleX = scale
                    scaleY = scale
                }
            }
        }
    }
}

class LazyCardStackMeasureResult(
    val currentItem: LazyCardMeasuredItem?,
    val itemCount: Int,
)
