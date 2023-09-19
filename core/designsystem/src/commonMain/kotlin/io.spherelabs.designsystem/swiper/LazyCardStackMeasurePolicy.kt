package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.round
import com.haroncode.lazycardstack.LazyCardStackState

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun rememberLazyCardStackMeasurePolicy(
    state: LazyCardStackState,
    itemProviderLambda: () -> LazyLayoutItemProvider,
) = remember<LazyLayoutMeasureScope.(Constraints) -> MeasureResult>(
    state,
    itemProviderLambda
) {
    { containerConstraints ->
        val itemProvider = itemProviderLambda()
        val itemsCount = itemProvider.itemCount
        var firstVisibleItemIndex: Int

        Snapshot.withoutReadObservation {
            firstVisibleItemIndex = state.updateScrollPositionIfTheFirstItemWasDeleted(
                itemProvider,
                state.visibleItemIndex
            )
        }

        val childConstraints = Constraints(
            maxWidth = containerConstraints.maxWidth,
            maxHeight = containerConstraints.maxHeight
        )

        if (firstVisibleItemIndex >= itemsCount) {
            // the data set has been updated and now we have less items that we were
            // scrolled to before
            firstVisibleItemIndex = itemsCount - 1
        }

        val indexRange = when {
            firstVisibleItemIndex < itemsCount - 1 -> firstVisibleItemIndex..firstVisibleItemIndex + 1
            firstVisibleItemIndex == itemsCount - 1 -> firstVisibleItemIndex..firstVisibleItemIndex
            else -> IntRange.EMPTY
        }

        state.swiperState.isEnabled = indexRange.count() > 1

        val visibleItems = indexRange.mapIndexed { relativeIndex, itemIndex ->
            val placeables = measure(itemIndex, childConstraints)
            val key = itemProvider.getKey(itemIndex)

            LazyCardMeasuredItem(
                relativeIndex = relativeIndex,
                dragOffset = state.offset.round(),
                scale = state.scale,
                rotation = state.rotation,
                key = key,
                placeables = placeables
            )
        }

        val width = containerConstraints.maxWidth
        val height = containerConstraints.maxHeight

        state.premeasureConstraints = containerConstraints

        layout(width, height) {
            visibleItems.forEach { item -> item.place(this) }
            val measureResult = LazyCardStackMeasureResult(
                currentItem = visibleItems.firstOrNull(),
                itemCount = itemsCount
            )
            state.applyMeasureResult(measureResult)
        }
    }
}
