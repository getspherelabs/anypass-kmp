package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.MutatorMutex
import androidx.compose.ui.geometry.Offset

interface SwiperDraggableState {

  suspend fun drag(
    dragPriority: MutatePriority = MutatePriority.Default,
    block: suspend SwiperDragScope.() -> Unit
  )
}

interface SwiperDragScope {

  fun dragBy(offset: Offset)
}

fun SwiperDraggableState(onDelta: (Offset) -> Unit): SwiperDraggableState {
  return DefaultSwiperDraggableState(onDelta)
}

private class DefaultSwiperDraggableState(val onDelta: (Offset) -> Unit) : SwiperDraggableState {

  private val dragScope: SwiperDragScope =
    object : SwiperDragScope {
      override fun dragBy(offset: Offset): Unit = onDelta(offset)
    }

  private val scrollMutex = MutatorMutex()

  override suspend fun drag(
    dragPriority: MutatePriority,
    block: suspend SwiperDragScope.() -> Unit
  ) {
    scrollMutex.mutateWith(dragScope, dragPriority, block)
  }
}
