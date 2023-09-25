package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.IntervalList
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
import androidx.compose.foundation.lazy.layout.MutableIntervalList
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
internal class LKCardStackIntervalContent(
  content: LKCardStackScope.() -> Unit,
) : LazyLayoutIntervalContent<LKCardStackInterval>(), LKCardStackScope {

  private val _intervals = MutableIntervalList<LKCardStackInterval>()
  override val intervals: IntervalList<LKCardStackInterval> = _intervals

  init {
    apply(content)
  }

  override fun items(
    count: Int,
    key: ((index: Int) -> Any),
    contentType: (index: Int) -> Any?,
    itemContent: @Composable LKCardStackItemScope.(index: Int) -> Unit
  ) {
    _intervals.addInterval(
      count,
      LKCardStackInterval(key = key, type = contentType, item = itemContent)
    )
  }

  override fun item(
    key: ((index: Int) -> Any),
    contentType: (index: Int) -> Any?,
    itemContent: @Composable LKCardStackItemScope.(index: Int) -> Unit
  ) {
    _intervals.addInterval(
      1,
      LKCardStackInterval(key = key, type = contentType, item = itemContent)
    )
  }
}

@OptIn(ExperimentalFoundationApi::class)
internal class LKCardStackInterval(
  override val key: ((index: Int) -> Any)?,
  override val type: ((index: Int) -> Any?),
  val item: @Composable LKCardStackItemScope.(index: Int) -> Unit
) : LazyLayoutIntervalContent.Interval
