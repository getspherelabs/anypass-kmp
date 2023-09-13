package io.spherelabs.lockerkmp.components.collapsingToolbar

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Velocity
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Stable
class CollapsingToolbarState(
    initial: Int = Int.MAX_VALUE
) : ScrollableState {
    /**
     * [height] indicates current height of the toolbar.
     */
    var height: Int by mutableStateOf(initial)
        private set

    /**
     * [minHeight] indicates the minimum height of the collapsing toolbar. The toolbar
     * may collapse its height to [minHeight] but not smaller. This size is determined by
     * the smallest child.
     */
    var minHeight: Int
        get() = minHeightState
        internal set(value) {
            minHeightState = value

            if (height < value) {
                height = value
            }
        }

    /**
     * [maxHeight] indicates the maximum height of the collapsing toolbar. The toolbar
     * may expand its height to [maxHeight] but not larger. This size is determined by
     * the largest child.
     */
    var maxHeight: Int
        get() = maxHeightState
        internal set(value) {
            maxHeightState = value

            if (value < height) {
                height = value
            }
        }

    private var maxHeightState by mutableStateOf(Int.MAX_VALUE)
    private var minHeightState by mutableStateOf(0)

    val progress: Float
        get() =
            if (minHeight == maxHeight) {
                0f.coerceIn(minimumValue = 0.0f, maximumValue = 1.0f)
            } else {
                ((height - minHeight).toFloat() / (maxHeight - minHeight)).coerceIn(0f, 1f)
            }

    private val scrollableState = ScrollableState { value ->
        val consume = if (value < 0) {
            max(minHeight.toFloat() - height, value)
        } else {
            min(maxHeight.toFloat() - height, value)
        }

        val current = consume + deferredConsumption
        val currentInt = current.toInt()

        if (current.absoluteValue > 0) {
            height += currentInt
            deferredConsumption = current - currentInt
        }

        consume
    }

    private var deferredConsumption: Float = 0f

    /**
     * @return consumed scroll value is returned
     */
    @Deprecated(
        message = "feedScroll() is deprecated, use dispatchRawDelta() instead.",
        replaceWith = ReplaceWith("dispatchRawDelta(value)")
    )
    fun feedScroll(value: Float): Float = dispatchRawDelta(value)

    suspend fun expand(duration: Int = 200) {
        val anim = AnimationState(height.toFloat())

        scroll {
            var prev = anim.value
            anim.animateTo(maxHeight.toFloat(), tween(duration)) {
                scrollBy(value - prev)
                prev = value
            }
        }
    }

    suspend fun collapse(duration: Int = 200) {
        val anim = AnimationState(height.toFloat())

        scroll {
            var prev = anim.value
            anim.animateTo(minHeight.toFloat(), tween(duration)) {
                scrollBy(value - prev)
                prev = value
            }
        }
    }

    /**
     * @return Remaining velocity after fling
     */
    suspend fun fling(flingBehavior: FlingBehavior, velocity: Float): Float {
        var left = velocity
        scroll {
            with(flingBehavior) {
                left = performFling(left)
            }
        }

        return left
    }

    override val isScrollInProgress: Boolean
        get() = scrollableState.isScrollInProgress

    override fun dispatchRawDelta(delta: Float): Float = scrollableState.dispatchRawDelta(delta)

    override suspend fun scroll(
        scrollPriority: MutatePriority,
        block: suspend ScrollScope.() -> Unit
    ) = scrollableState.scroll(scrollPriority, block)
}

@Composable
fun rememberCollapsingToolbarState(
    initial: Int = Int.MAX_VALUE
): CollapsingToolbarState {
    return remember {
        CollapsingToolbarState(
            initial = initial
        )
    }
}

@Composable
fun CollapsingToolbar(
    modifier: Modifier = Modifier,
    clipToBounds: Boolean = true,
    collapsingToolbarState: CollapsingToolbarState,
    content: @Composable CollapsingToolbarScope.() -> Unit
) {
    val measurePolicy = remember(collapsingToolbarState) {
        CollapsingToolbarMeasurePolicy(collapsingToolbarState)
    }

    Layout(
        content = { CollapsingToolbarScopeInstance.content() },
        measurePolicy = measurePolicy,
        modifier = modifier.then(
            if (clipToBounds) {
                Modifier.clipToBounds()
            } else {
                Modifier
            }
        )
    )
}

private class CollapsingToolbarMeasurePolicy(
    private val collapsingToolbarState: CollapsingToolbarState
) : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val placeables = measurables.map {
            it.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxHeight = Constraints.Infinity
                )
            )
        }

        val placeStrategy = measurables.map { it.parentData }

        val minHeight = placeables.minOfOrNull { it.height }
            ?.coerceIn(constraints.minHeight, constraints.maxHeight) ?: 0

        val maxHeight = placeables.maxOfOrNull { it.height }
            ?.coerceIn(constraints.minHeight, constraints.maxHeight) ?: 0

        val maxWidth = placeables.maxOfOrNull { it.width }
            ?.coerceIn(constraints.minWidth, constraints.maxWidth) ?: 0

        collapsingToolbarState.also {
            it.minHeight = minHeight
            it.maxHeight = maxHeight
        }

        val height = collapsingToolbarState.height
        return layout(maxWidth, height) {
            val progress = collapsingToolbarState.progress

            placeables.forEachIndexed { i, placeable ->
                val strategy = placeStrategy[i]
                if (strategy is CollapsingToolbarData) {
                    strategy.progressListener?.onProgressUpdate(progress)
                }

                when (strategy) {
                    is CollapsingToolbarRoadData -> {
                        val collapsed = strategy.whenCollapsed
                        val expanded = strategy.whenExpanded

                        val collapsedOffset = collapsed.align(
                            size = IntSize(placeable.width, placeable.height),
                            space = IntSize(maxWidth, height),
                            layoutDirection = layoutDirection
                        )

                        val expandedOffset = expanded.align(
                            size = IntSize(placeable.width, placeable.height),
                            space = IntSize(maxWidth, height),
                            layoutDirection = layoutDirection
                        )

                        val offset = collapsedOffset + (expandedOffset - collapsedOffset) * progress

                        placeable.place(offset.x, offset.y)
                    }

                    is CollapsingToolbarParallaxData ->
                        placeable.placeRelative(
                            x = 0,
                            y = -((maxHeight - minHeight) * (1 - progress) * strategy.ratio).roundToInt()
                        )

                    else -> placeable.placeRelative(0, 0)
                }
            }
        }
    }
}

interface CollapsingToolbarScope {
    fun Modifier.progress(listener: ProgressListener): Modifier

    fun Modifier.road(whenCollapsed: Alignment, whenExpanded: Alignment): Modifier

    fun Modifier.parallax(ratio: Float = 0.2f): Modifier

    fun Modifier.pin(): Modifier
}

internal object CollapsingToolbarScopeInstance : CollapsingToolbarScope {
    override fun Modifier.progress(listener: ProgressListener): Modifier {
        return this.then(ProgressUpdateListenerModifier(listener))
    }

    override fun Modifier.road(whenCollapsed: Alignment, whenExpanded: Alignment): Modifier {
        return this.then(RoadModifier(whenCollapsed, whenExpanded))
    }

    override fun Modifier.parallax(ratio: Float): Modifier {
        return this.then(ParallaxModifier(ratio))
    }

    override fun Modifier.pin(): Modifier {
        return this.then(PinModifier())
    }
}

internal class RoadModifier(
    private val whenCollapsed: Alignment,
    private val whenExpanded: Alignment
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return CollapsingToolbarRoadData(
            this@RoadModifier.whenCollapsed, this@RoadModifier.whenExpanded,
            (parentData as? CollapsingToolbarData)?.progressListener
        )
    }
}

internal class ParallaxModifier(
    private val ratio: Float
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return CollapsingToolbarParallaxData(
            ratio,
            (parentData as? CollapsingToolbarData)?.progressListener
        )
    }
}

internal class PinModifier : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return CollapsingToolbarPinData((parentData as? CollapsingToolbarData)?.progressListener)
    }
}

internal class ProgressUpdateListenerModifier(
    private val listener: ProgressListener
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return CollapsingToolbarProgressData(listener)
    }
}

fun interface ProgressListener {
    fun onProgressUpdate(value: Float)
}

internal sealed class CollapsingToolbarData(
    var progressListener: ProgressListener?
)

internal class CollapsingToolbarProgressData(
    progressListener: ProgressListener?
) : CollapsingToolbarData(progressListener)

internal class CollapsingToolbarRoadData(
    var whenCollapsed: Alignment,
    var whenExpanded: Alignment,
    progressListener: ProgressListener? = null
) : CollapsingToolbarData(progressListener)

internal class CollapsingToolbarPinData(
    progressListener: ProgressListener? = null
) : CollapsingToolbarData(progressListener)

internal class CollapsingToolbarParallaxData(
    var ratio: Float,
    progressListener: ProgressListener? = null
) : CollapsingToolbarData(progressListener)

@Stable
class CollapsingToolbarScaffoldState(
    val toolbarState: CollapsingToolbarState,
    initialOffsetY: Int = 0
) {
    val offsetY: Int
        get() = offsetYState.value

    internal val offsetYState = mutableStateOf(initialOffsetY)
}

private class CollapsingToolbarScaffoldStateSaver:
    Saver<CollapsingToolbarScaffoldState, List<Any>> {
    override fun restore(value: List<Any>): CollapsingToolbarScaffoldState =
        CollapsingToolbarScaffoldState(
            CollapsingToolbarState(value[0] as Int),
            value[1] as Int
        )

    override fun SaverScope.save(value: CollapsingToolbarScaffoldState): List<Any> =
        listOf(
            value.toolbarState.height,
            value.offsetY
        )
}

@Composable
fun rememberCollapsingToolbarScaffoldState(
    toolbarState: CollapsingToolbarState = rememberCollapsingToolbarState()
): CollapsingToolbarScaffoldState {
    return rememberSaveable(toolbarState, saver = CollapsingToolbarScaffoldStateSaver()) {
        CollapsingToolbarScaffoldState(toolbarState)
    }
}

interface CollapsingToolbarScaffoldScope {
    fun Modifier.align(alignment: Alignment): Modifier
}

@Composable
fun CollapsingToolbarScaffold(
    modifier: Modifier,
    state: CollapsingToolbarScaffoldState,
    scrollStrategy: ScrollStrategy,
    enabled: Boolean = true,
    toolbarModifier: Modifier = Modifier,
    toolbarClipToBounds: Boolean = true,
    toolbar: @Composable CollapsingToolbarScope.() -> Unit,
    body: @Composable CollapsingToolbarScaffoldScope.() -> Unit
) {
    val flingBehavior = ScrollableDefaults.flingBehavior()
    val layoutDirection = LocalLayoutDirection.current

    val nestedScrollConnection = remember(scrollStrategy, state) {
        scrollStrategy.create(state.offsetYState, state.toolbarState, flingBehavior)
    }

    val toolbarState = state.toolbarState

    Layout(
        content = {
            CollapsingToolbar(
                modifier = toolbarModifier,
                clipToBounds = toolbarClipToBounds,
                collapsingToolbarState = toolbarState,
            ) {
                toolbar()
            }

            CollapsingToolbarScaffoldScopeInstance.body()
        },
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier.nestedScroll(nestedScrollConnection)
                } else {
                    Modifier
                }
            )
    ) { measurables, constraints ->
        check(measurables.size >= 2) {
            "the number of children should be at least 2: toolbar, (at least one) body"
        }

        val toolbarConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        val bodyConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
            maxHeight = when (scrollStrategy) {
                ScrollStrategy.ExitUntilCollapsed ->
                    (constraints.maxHeight - toolbarState.minHeight).coerceAtLeast(0)

                ScrollStrategy.EnterAlways, ScrollStrategy.EnterAlwaysCollapsed ->
                    constraints.maxHeight
            }
        )

        val toolbarPlaceable = measurables[0].measure(toolbarConstraints)

        val bodyMeasurables = measurables.subList(1, measurables.size)
        val childrenAlignments = bodyMeasurables.map {
            (it.parentData as? ScaffoldParentData)?.alignment
        }
        val bodyPlaceables = bodyMeasurables.map {
            it.measure(bodyConstraints)
        }

        val toolbarHeight = toolbarPlaceable.height

        val width = max(
            toolbarPlaceable.width,
            bodyPlaceables.maxOfOrNull { it.width } ?: 0
        ).coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = max(
            toolbarHeight,
            bodyPlaceables.maxOfOrNull { it.height } ?: 0
        ).coerceIn(constraints.minHeight, constraints.maxHeight)

        layout(width, height) {
            bodyPlaceables.forEachIndexed { index, placeable ->
                val alignment = childrenAlignments[index]

                if (alignment == null) {
                    placeable.placeRelative(0, toolbarHeight + state.offsetY)
                } else {
                    val offset = alignment.align(
                        size = IntSize(placeable.width, placeable.height),
                        space = IntSize(width, height),
                        layoutDirection = layoutDirection
                    )
                    placeable.place(offset)
                }
            }
            toolbarPlaceable.placeRelative(0, state.offsetY)
        }
    }
}

internal object CollapsingToolbarScaffoldScopeInstance: CollapsingToolbarScaffoldScope {
    override fun Modifier.align(alignment: Alignment): Modifier =
        this.then(ScaffoldChildAlignmentModifier(alignment))
}

private class ScaffoldChildAlignmentModifier(
    private val alignment: Alignment
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return (parentData as? ScaffoldParentData) ?: ScaffoldParentData(alignment)
    }
}

private data class ScaffoldParentData(
    var alignment: Alignment? = null
)

enum class ScrollStrategy {
    EnterAlways {
        override fun create(
            offsetY: MutableState<Int>,
            toolbarState: CollapsingToolbarState,
            flingBehavior: FlingBehavior
        ): NestedScrollConnection =
            EnterAlwaysNestedScrollConnection(offsetY, toolbarState, flingBehavior)
    },
    EnterAlwaysCollapsed {
        override fun create(
            offsetY: MutableState<Int>,
            toolbarState: CollapsingToolbarState,
            flingBehavior: FlingBehavior
        ): NestedScrollConnection =
            EnterAlwaysCollapsedNestedScrollConnection(offsetY, toolbarState, flingBehavior)
    },
    ExitUntilCollapsed {
        override fun create(
            offsetY: MutableState<Int>,
            toolbarState: CollapsingToolbarState,
            flingBehavior: FlingBehavior
        ): NestedScrollConnection =
            ExitUntilCollapsedNestedScrollConnection(toolbarState, flingBehavior)
    };

    internal abstract fun create(
        offsetY: MutableState<Int>,
        toolbarState: CollapsingToolbarState,
        flingBehavior: FlingBehavior
    ): NestedScrollConnection
}

private class ScrollDelegate(
    private val offsetY: MutableState<Int>
) {
    private var scrollToBeConsumed: Float = 0f

    fun doScroll(delta: Float) {
        val scroll = scrollToBeConsumed + delta
        val scrollInt = scroll.toInt()

        scrollToBeConsumed = scroll - scrollInt

        offsetY.value += scrollInt
    }
}

internal class EnterAlwaysNestedScrollConnection(
    private val offsetY: MutableState<Int>,
    private val toolbarState: CollapsingToolbarState,
    private val flingBehavior: FlingBehavior
): NestedScrollConnection {
    private val scrollDelegate = ScrollDelegate(offsetY)
    //private val tracker = RelativeVelocityTracker(CurrentTimeProviderImpl())

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val dy = available.y

        val toolbar = toolbarState.height.toFloat()
        val offset = offsetY.value.toFloat()

        // -toolbarHeight <= offsetY + dy <= 0
        val consume = if(dy < 0) {
            val toolbarConsumption = toolbarState.dispatchRawDelta(dy)
            val remaining = dy - toolbarConsumption
            val offsetConsumption = remaining.coerceAtLeast(-toolbar - offset)
            scrollDelegate.doScroll(offsetConsumption)

            toolbarConsumption + offsetConsumption
        }else{
            val offsetConsumption = dy.coerceAtMost(-offset)
            scrollDelegate.doScroll(offsetConsumption)

            val toolbarConsumption = toolbarState.dispatchRawDelta(dy - offsetConsumption)

            offsetConsumption + toolbarConsumption
        }

        return Offset(0f, consume)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val left = if(available.y > 0) {
            toolbarState.fling(flingBehavior, available.y)
        }else{
            // If velocity < 0, the main content should have a remaining scroll space
            // so the scroll resumes to the onPreScroll(..., Fling) phase. Hence we do
            // not need to process it at onPostFling() manually.
            available.y
        }

        return Velocity(x = 0f, y = available.y - left)
    }
}

internal class EnterAlwaysCollapsedNestedScrollConnection(
    private val offsetY: MutableState<Int>,
    private val toolbarState: CollapsingToolbarState,
    private val flingBehavior: FlingBehavior
): NestedScrollConnection {
    private val scrollDelegate = ScrollDelegate(offsetY)
    //private val tracker = RelativeVelocityTracker(CurrentTimeProviderImpl())

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val dy = available.y

        val consumed = if(dy > 0) { // expanding: offset -> body -> toolbar
            val offsetConsumption = dy.coerceAtMost(-offsetY.value.toFloat())
            scrollDelegate.doScroll(offsetConsumption)

            offsetConsumption
        }else{ // collapsing: toolbar -> offset -> body
            val toolbarConsumption = toolbarState.dispatchRawDelta(dy)
            val offsetConsumption = (dy - toolbarConsumption).coerceAtLeast(-toolbarState.height.toFloat() - offsetY.value)

            scrollDelegate.doScroll(offsetConsumption)

            toolbarConsumption + offsetConsumption
        }

        return Offset(0f, consumed)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val dy = available.y

        return if(dy > 0) {
            Offset(0f, toolbarState.dispatchRawDelta(dy))
        }else{
            Offset(0f, 0f)
        }
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        val dy = available.y

        val left = if(dy > 0) {
            // onPostFling() has positive available scroll value only called if the main scroll
            // has leftover scroll, i.e. the scroll of the main content has done. So we just process
            // fling if the available value is positive.
            toolbarState.fling(flingBehavior, dy)
        }else{
            dy
        }

        return Velocity(x = 0f, y = available.y - left)
    }
}

internal class ExitUntilCollapsedNestedScrollConnection(
    private val toolbarState: CollapsingToolbarState,
    private val flingBehavior: FlingBehavior
): NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val dy = available.y

        val consume = if(dy < 0) { // collapsing: toolbar -> body
            toolbarState.dispatchRawDelta(dy)
        }else{
            0f
        }

        return Offset(0f, consume)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val dy = available.y

        val consume = if(dy > 0) { // expanding: body -> toolbar
            toolbarState.dispatchRawDelta(dy)
        }else{
            0f
        }

        return Offset(0f, consume)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val left = if(available.y < 0) {
            toolbarState.fling(flingBehavior, available.y)
        }else{
            available.y
        }

        return Velocity(x = 0f, y = available.y - left)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        val velocity = available.y

        val left = if(velocity > 0) {
            toolbarState.fling(flingBehavior, velocity)
        }else{
            velocity
        }

        return Velocity(x = 0f, y = available.y - left)
    }
}
