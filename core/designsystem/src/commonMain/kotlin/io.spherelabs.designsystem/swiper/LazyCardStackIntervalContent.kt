package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.IntervalList
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
import androidx.compose.foundation.lazy.layout.MutableIntervalList
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
internal class LazyCardStackIntervalContent(
    content: LazyCardStackScope.() -> Unit,
) : LazyLayoutIntervalContent<LazyCardStackInterval>(), LazyCardStackScope {

    private val _intervals = MutableIntervalList<LazyCardStackInterval>()
    override val intervals: IntervalList<LazyCardStackInterval> = _intervals

    init {
        apply(content)
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any),
        contentType: (index: Int) -> Any?,
        itemContent: @Composable LazyCardStackItemScope.(index: Int) -> Unit
    ) {
        _intervals.addInterval(
            count,
            LazyCardStackInterval(
                key = key,
                type = contentType,
                item = itemContent
            )
        )
    }

    override fun item(
        key: ((index: Int) -> Any),
        contentType: (index: Int) -> Any?,
        itemContent: @Composable LazyCardStackItemScope.(index: Int) -> Unit
    ) {
        _intervals.addInterval(
            1,
            LazyCardStackInterval(
                key = key,
                type = contentType,
                item = itemContent
            )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
internal class LazyCardStackInterval(
    override val key: ((index: Int) -> Any)?,
    override val type: ((index: Int) -> Any?),
    val item: @Composable LazyCardStackItemScope.(index: Int) -> Unit
) : LazyLayoutIntervalContent.Interval
