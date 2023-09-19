package io.spherelabs.designsystem.swiper

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.Remeasurement
import androidx.compose.ui.layout.RemeasurementModifier
import androidx.compose.ui.unit.Constraints
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Stable
@Composable
fun useLKCardStackState(
    firstVisibleItemIndex: Int = 0,
    animationSpec: AnimationSpec<Offset> = SpringSpec(),
): LKCardStackState {
    return rememberSaveable(saver = LKCardStackState.Saver(animationSpec)) {
        LKCardStackState(
            firstVisibleItemIndex = firstVisibleItemIndex,
            animationSpec = animationSpec
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Stable
class LKCardStackState internal constructor(
    firstVisibleItemIndex: Int = 0,
    private val animationSpec: AnimationSpec<Offset>,
) {

    internal val swiperState = LKSwiperState(animationSpec)

    val offset: Offset get() = swiperState.offset

    val rotation: Float get() = swiperState.rotation

    val scale: Float get() = swiperState.scale

    val isAnimationRunning: Boolean get() = swiperState.isAnimationRunning

    var itemsCount: Int by  mutableStateOf(0)


    var visibleItemIndex by  mutableStateOf(firstVisibleItemIndex)


    private var lastKnownFirstItemKey: Any? = null

    internal var remeasurement: Remeasurement? by mutableStateOf(null)
        private set


    internal val awaitLayoutModifier = AwaitFirstLayoutModifier()

    internal var premeasureConstraints = Constraints()

    internal val prefetchState = LazyLayoutPrefetchState()

    internal val remeasurementModifier = object : RemeasurementModifier {
        override fun onRemeasurementAvailable(remeasurement: Remeasurement) {
            this@LKCardStackState.remeasurement = remeasurement
        }
    }

    suspend fun animateToBack(
        fromDirection: LKSwipeDirection,
        animation: AnimationSpec<Offset> = animationSpec,
    ) {
        awaitLayoutModifier.waitForFirstLayout()

        val nextIndex = visibleItemIndex - 1
        val realIndex = nextIndex.coerceIn(0, itemsCount - 1)

        if (visibleItemIndex == realIndex) return

        visibleItemIndex = realIndex
        lastKnownFirstItemKey = null

        val target = swiperState.offsetByDirection(fromDirection)
        swiperState.snapTo(target)

        swiperState.animateToCenter(animation)
        remeasurement?.forceRemeasure()
    }

    suspend fun snapTo(index: Int) {
        awaitLayoutModifier.waitForFirstLayout()

        val realIndex = index.coerceIn(0, itemsCount - 1)

        visibleItemIndex = realIndex
        println("Visible item index $visibleItemIndex")
        lastKnownFirstItemKey = null

        swiperState.snapTo(Offset.Zero)

        prefetchState.schedulePrefetch(visibleItemIndex + 2, premeasureConstraints)
        remeasurement?.forceRemeasure()
    }

    suspend fun animateToNext(
        direction: LKSwipeDirection,
        animation: AnimationSpec<Offset> = animationSpec,
    ) {
        awaitLayoutModifier.waitForFirstLayout()

        val nextIndex = visibleItemIndex + 1
        val realIndex = nextIndex.coerceIn(0, itemsCount - 1)

        swiperState.animateTo(direction, animation)

        visibleItemIndex = realIndex
        lastKnownFirstItemKey = null

        swiperState.snapTo(Offset.Zero)

        prefetchState.schedulePrefetch(visibleItemIndex + 2, premeasureConstraints)
        remeasurement?.forceRemeasure()
    }

    internal fun applyMeasureResult(result: LazyCardStackMeasureResult) {
        itemsCount = result.itemCount
        lastKnownFirstItemKey = result.currentItem?.key
    }

    internal fun updateScrollPositionIfTheFirstItemWasDeleted(
        itemProvider: LazyLayoutItemProvider,
        index: Int
    ): Int {
        if (itemsCount < itemProvider.itemCount) {
            return index
        }

        val newIndex = itemProvider.findIndexByKey(
            key = lastKnownFirstItemKey,
            lastKnownIndex = index,
        )
        if (index != newIndex) {
            visibleItemIndex = newIndex
        }
        return newIndex
    }

    private fun LazyLayoutItemProvider.findIndexByKey(
        key: Any?,
        lastKnownIndex: Int,
    ): Int {
        if (key == null) {
            return lastKnownIndex
        }
        if (lastKnownIndex < itemCount && key == getKey(lastKnownIndex)) {
            return lastKnownIndex
        }
        val newIndex = getIndex(key)
        if (newIndex != -1) {
            return newIndex
        }
        return lastKnownIndex
    }

    companion object {

        fun Saver(
            animationSpec: AnimationSpec<Offset>,
        ) = Saver<LKCardStackState, Int>(
            save = { it.visibleItemIndex },
            restore = {
                LKCardStackState(
                    firstVisibleItemIndex = it,
                    animationSpec = animationSpec
                )
            }
        )
    }
}


internal class AwaitFirstLayoutModifier : OnGloballyPositionedModifier {
    private var wasPositioned = false
    private var continuation: Continuation<Unit>? = null

    suspend fun waitForFirstLayout() {
        if (!wasPositioned) {
            val oldContinuation = continuation
            suspendCoroutine { continuation = it }
            oldContinuation?.resume(Unit)
        }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (!wasPositioned) {
            wasPositioned = true
            continuation?.resume(Unit)
            continuation = null
        }
    }
}

