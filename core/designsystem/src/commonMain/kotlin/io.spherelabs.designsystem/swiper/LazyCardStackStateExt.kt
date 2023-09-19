package io.spherelabs.designsystem.swiper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import com.haroncode.lazycardstack.LazyCardStackState

@Composable
fun LazyCardStackState.PagingObserve(
    prefetchCount: Int = 10,
    onLoadMore: (Int) -> Unit
) {
    val previousTotalItemCount = rememberSaveable(this) { mutableIntStateOf(0) }
    LaunchedEffect(this) {
        snapshotFlow { visibleItemIndex }
            .collect { firstIndex ->
                if (itemsCount < prefetchCount) return@collect
                val countHasChanged = previousTotalItemCount.intValue != itemsCount

                if (countHasChanged && firstIndex + prefetchCount > itemsCount) {
                    previousTotalItemCount.intValue = itemsCount
                    onLoadMore(firstIndex)
                }
            }
    }
}
