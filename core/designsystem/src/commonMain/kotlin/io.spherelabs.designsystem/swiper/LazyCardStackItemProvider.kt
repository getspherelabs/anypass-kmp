package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.haroncode.lazycardstack.LazyCardStackState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberLazyCardStackItemProviderLambda(
    state: LazyCardStackState,
    customLazyListScope: LazyCardStackScope.() -> Unit
): () -> LazyLayoutItemProvider {
    val scope = LazyCardStackItemScopeImpl()
    val latestContent = rememberUpdatedState(customLazyListScope)
    return remember(state) {
        val intervalContentState = derivedStateOf(referentialEqualityPolicy()) {
            LazyCardStackIntervalContent(latestContent.value)
        }
        val itemProviderState = derivedStateOf(referentialEqualityPolicy()) {
            val intervalContent = intervalContentState.value
            val start = (state.visibleItemIndex - 10).coerceAtLeast(0)
            val map = NearestRangeKeyIndexMap(
                nearestRange = start until start + 10,
                intervalContent = intervalContent
            )
            LazyCardStackItemProviderImpl(
                intervalContent = intervalContent,
                keyIndexMap = map,
                lazyCardItemScope = scope
            )
        }
        itemProviderState::value
    }
}

@ExperimentalFoundationApi
private class LazyCardStackItemProviderImpl(
    private val intervalContent: LazyCardStackIntervalContent,
    private val keyIndexMap: NearestRangeKeyIndexMap,
    private val lazyCardItemScope: LazyCardStackItemScope
) : LazyLayoutItemProvider {

    override val itemCount: Int get() = intervalContent.itemCount

    @Composable
    override fun Item(index: Int, key: Any) {
        intervalContent.withInterval(index) { _, content ->
            content.item(lazyCardItemScope, index)
        }
    }

    override fun getKey(index: Int): Any =
        keyIndexMap.getKey(index) ?: intervalContent.getKey(index)

    override fun getContentType(index: Int): Any? = intervalContent.getContentType(index)

    override fun getIndex(key: Any): Int = keyIndexMap.getIndex(key)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LazyCardStackItemProviderImpl) return false

        // the identity of this class is represented by intervalContent object.
        // having equals() allows us to skip items recomposition when intervalContent didn't change
        return intervalContent == other.intervalContent
    }

    override fun hashCode(): Int {
        return intervalContent.hashCode()
    }
}
