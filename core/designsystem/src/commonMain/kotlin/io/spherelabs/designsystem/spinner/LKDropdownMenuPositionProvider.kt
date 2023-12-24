package io.spherelabs.designsystem.spinner

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

@Immutable
internal data class LKDropdownMenuPositionProvider(
    val contentOffset: DpOffset,
    val density: Density,
    val onPositionCalculated: (IntRect, IntRect) -> Unit = { _, _ -> }
) : PopupPositionProvider {

  override fun calculatePosition(
      anchorBounds: IntRect,
      windowSize: IntSize,
      layoutDirection: LayoutDirection,
      popupContentSize: IntSize
  ): IntOffset {

    val verticalMargin = with(density) { LKDropdownTokens.MenuVerticalMargin.roundToPx() }

    val contentOffsetX = with(density) { contentOffset.x.roundToPx() }
    val contentOffsetY = with(density) { contentOffset.y.roundToPx() }

    val toRight = anchorBounds.left + contentOffsetX
    val toLeft = anchorBounds.right - contentOffsetX - popupContentSize.width

    val toDisplayRight = windowSize.width - popupContentSize.width
    val toDisplayLeft = 0

    val x =
        if (layoutDirection == LayoutDirection.Ltr) {
              sequenceOf(
                  toRight, toLeft, if (anchorBounds.left >= 0) toDisplayRight else toDisplayLeft)
            } else {
              sequenceOf(
                  toLeft,
                  toRight,
                  if (anchorBounds.right <= windowSize.width) toDisplayLeft else toDisplayRight)
            }
            .firstOrNull { it >= 0 && it + popupContentSize.width <= windowSize.width }
            ?: toLeft

    val toBottom = maxOf(anchorBounds.bottom + contentOffsetY, verticalMargin)
    val toTop = anchorBounds.top - contentOffsetY - popupContentSize.height
    val toCenter = anchorBounds.top - popupContentSize.height / 2
    val toDisplayBottom = windowSize.height - popupContentSize.height - verticalMargin
    val y =
        sequenceOf(toBottom, toTop, toCenter, toDisplayBottom).firstOrNull {
          it >= verticalMargin && it + popupContentSize.height <= windowSize.height - verticalMargin
        }
            ?: toTop

    onPositionCalculated(
        anchorBounds, IntRect(x, y, x + popupContentSize.width, y + popupContentSize.height))
    return IntOffset(x, y)
  }
}
