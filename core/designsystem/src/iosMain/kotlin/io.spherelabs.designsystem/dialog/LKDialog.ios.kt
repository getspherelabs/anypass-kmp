package io.spherelabs.designsystem.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.DrawerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import io.spherelabs.designsystem.utils.LocalScreenConfiguration
import io.spherelabs.designsystem.utils.ScreenConfiguration
import kotlin.math.min
import kotlinx.atomicfu.atomic
import kotlinx.cinterop.useContents
import org.jetbrains.skiko.SkikoKey
import platform.UIKit.UIScreen

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
@Composable
internal actual fun LKDialog(
  onDismissRequest: () -> Unit,
  properties: LKDialogProperties,
  content: @Composable () -> Unit,
) {
  val size = remember {
    UIScreen.mainScreen.bounds.useContents { IntSize(size.width.toInt(), size.height.toInt()) }
  }
  CompositionLocalProvider(
    LocalScreenConfiguration provides ScreenConfiguration(size.width, size.height, size)
  ) {
    Popup(
      popupPositionProvider = IosPopupPositionProvider,
      onDismissRequest = onDismissRequest,
      properties = PopupProperties(focusable = true),
      onPreviewKeyEvent = { false },
      onKeyEvent = {
        if (
          properties.dismissOnBackPress &&
            it.type == KeyEventType.KeyDown &&
            it.nativeKeyEvent.key == SkikoKey.KEY_ESCAPE
        ) {
          onDismissRequest()
          true
        } else {
          false
        }
      }
    ) {
      Box(
        modifier = Modifier.fillMaxSize().background(DrawerDefaults.scrimColor),
        contentAlignment = Alignment.Center
      ) {
        if (properties.dismissOnClickOutside) {
          Box(
            modifier =
              Modifier.fillMaxSize().pointerInput(onDismissRequest) {
                detectTapGestures(onTap = { onDismissRequest() })
              }
          )
        }
        content()
      }
    }
  }
}

object IosPopupPositionProvider : PopupPositionProvider {
  override fun calculatePosition(
    anchorBounds: IntRect,
    windowSize: IntSize,
    layoutDirection: LayoutDirection,
    popupContentSize: IntSize
  ): IntOffset = IntOffset.Zero
}

internal actual fun Modifier.dialogMaxSize(maxHeight: Dp): Modifier =
  sizeIn(maxHeight = maxHeight, maxWidth = 560.dp)

@Composable internal actual fun getDialogShape(shape: Shape): Shape = shape

internal actual fun Modifier.dialogHeight(): Modifier = wrapContentHeight()

actual class AtomicInt actual constructor(initialValue: Int) : Number() {
  private val value = atomic(initialValue)

  actual constructor() : this(0)

  actual fun set(newValue: Int) {
    value.value = newValue
  }

  actual fun getAndIncrement(): Int = value.getAndIncrement()

  override fun toByte(): Byte = value.value.toByte()

  override fun toChar(): Char = value.value.toChar()

  override fun toDouble(): Double = value.value.toDouble()

  override fun toFloat(): Float = value.value.toFloat()

  override fun toInt(): Int = value.value

  override fun toLong(): Long = value.value.toLong()

  override fun toShort(): Short = value.value.toShort()
}

internal actual fun getLayoutHeight(maxHeightPx: Int, layoutHeight: Int): Int {
  return min(maxHeightPx, layoutHeight)
}
