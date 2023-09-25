package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
import androidx.compose.foundation.lazy.layout.getDefaultLazyLayoutKey

@OptIn(ExperimentalFoundationApi::class)
internal class NearestRangeKeyIndexMap(
  nearestRange: IntRange,
  intervalContent: LazyLayoutIntervalContent<*>
) {
  private val map: Map<Any, Int>
  private val keys: Array<Any?>
  private val keysStartIndex: Int

  init {
    // Traverses the interval [list] in order to create a mapping from the key to the index for
    // all the indexes in the passed [range].
    val list = intervalContent.intervals
    val first = nearestRange.first
    check(first >= 0)
    val last = minOf(nearestRange.last, list.size - 1)
    if (last < first) {
      map = emptyMap()
      keys = emptyArray()
      keysStartIndex = 0
    } else {
      keys = arrayOfNulls<Any?>(last - first + 1)
      keysStartIndex = first
      map =
        hashMapOf<Any, Int>().also { map ->
          list.forEach(
            fromIndex = first,
            toIndex = last,
          ) {
            val keyFactory = it.value.key
            val start = maxOf(first, it.startIndex)
            val end = minOf(last, it.startIndex + it.size - 1)
            for (i in start..end) {
              val key = keyFactory?.invoke(i - it.startIndex) ?: getDefaultLazyLayoutKey(i)
              map[key] = i
              keys[i - keysStartIndex] = key
            }
          }
        }
    }
  }

  fun getIndex(key: Any): Int = map.getOrElse(key) { -1 }

  fun getKey(index: Int) = keys.getOrElse(index - keysStartIndex) { null }
}
