package io.spherelabs.designsystem.swiper

import androidx.compose.runtime.Composable

interface LKCardStackScope {

  fun items(
      count: Int,
      key: ((index: Int) -> Any),
      contentType: (index: Int) -> Any? = { null },
      itemContent: @Composable LKCardStackItemScope.(index: Int) -> Unit
  )

  fun item(
      key: ((index: Int) -> Any),
      contentType: (index: Int) -> Any? = { null },
      itemContent: @Composable LKCardStackItemScope.(index: Int) -> Unit
  )
}

inline fun <T> LKCardStackScope.items(
    items: List<T>,
    noinline key: ((item: T) -> Any),
    noinline contentType: (index: Int) -> Any? = { null },
    crossinline itemContent: @Composable LKCardStackItemScope.(item: T) -> Unit
) =
    items(
        count = items.size, key = { index: Int -> key(items[index]) }, contentType = contentType) {
            index ->
          itemContent(items[index])
        }

inline fun <T> LKCardStackScope.itemsIndexed(
    items: List<T>,
    noinline key: (index: Int, item: T) -> Any,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LKCardStackItemScope.(index: Int, item: T) -> Unit
) =
    items(
        count = items.size,
        key = { index: Int -> key(index, items[index]) },
        contentType = { index -> contentType(index, items[index]) }) {
          itemContent(it, items[it])
        }
