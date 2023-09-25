package io.spherelabs.designsystem.slider

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.*

@Composable
fun LKSlider(
  modifier: Modifier = Modifier,
  value: Float,
  onValueChange: (Float, Offset) -> Unit,
  enabled: Boolean = true,
  valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
  steps: Int = 0,
  onValueChangeFinished: (() -> Unit)? = null,
  trackHeight: Dp = 8.dp,
  colors: LKSliderColors = LKSliderDefaults.sliderColors(),
  borderStroke: BorderStroke? = null,
  drawInactiveTrack: Boolean = true,
  coerceThumbInTrack: Boolean = false,
  thumb: @Composable () -> Unit
) {
  BoxWithConstraints {
    LKSliderLayout(
      modifier =
        modifier
          .minimumInteractiveComponentSize()
          .requiredSizeIn(
            minWidth = 5.dp * 2,
            minHeight = 5.dp * 2,
          ),
      thumb = { thumb() }
    ) { thumbSize: IntSize, constraints: Constraints ->
      require(steps >= 0) { "steps should be >= 0" }

      val onValueChangeState = rememberUpdatedState(onValueChange)

      val tickFractions = remember(steps) { stepsToTickFractions(steps) }

      val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

      val width = constraints.maxWidth.toFloat()
      val thumbHalfWidthPx = (thumbSize.width / 2).toFloat()
      val thumbHeightPx = thumbSize.height.toFloat()

      val trackStart: Float
      val trackEnd: Float

      val strokeRadius: Float

      with(LocalDensity.current) {
        strokeRadius = trackHeight.toPx() / 2
        trackStart = thumbHalfWidthPx.coerceAtLeast(strokeRadius)
        trackEnd = width - trackStart
      }

      fun scaleToUserValue(offset: Float) =
        scale(trackStart, trackEnd, offset, valueRange.start, valueRange.endInclusive)

      fun scaleToOffset(userValue: Float) =
        scale(valueRange.start, valueRange.endInclusive, userValue, trackStart, trackEnd)

      val rawOffset = remember { mutableStateOf(scaleToOffset(value)) }

      CorrectValueSideEffect(::scaleToOffset, valueRange, trackStart..trackEnd, rawOffset, value)

      val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
      val fraction = calculateFraction(valueRange.start, valueRange.endInclusive, coerced)

      val dragModifier =
        Modifier.pointerInput(Unit) {
            detectDragGestures(
              onDrag = { change: PointerInputChange, _: Offset ->
                if (enabled) {
                  rawOffset.value = if (!isRtl) change.position.x else trackEnd - change.position.x
                  val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                  onValueChangeState.value.invoke(
                    scaleToUserValue(offsetInTrack),
                    Offset(rawOffset.value.coerceIn(trackStart, trackEnd), strokeRadius)
                  )
                }
              },
              onDragEnd = {
                if (enabled) {
                  onValueChangeFinished?.invoke()
                }
              }
            )
          }
          .pointerInput(Unit) {
            detectTapGestures { position: Offset ->
              if (enabled) {
                rawOffset.value = if (!isRtl) position.x else trackEnd - position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(
                  scaleToUserValue(offsetInTrack),
                  Offset(rawOffset.value.coerceIn(trackStart, trackEnd), strokeRadius)
                )
                onValueChangeFinished?.invoke()
              }
            }
          }

      LKBasicSlider(
        enabled = enabled,
        fraction = fraction,
        trackStart = trackStart,
        trackEnd = trackEnd,
        tickFractions = tickFractions,
        colors = colors,
        trackHeight = trackHeight,
        thumbRadius = thumbHalfWidthPx,
        thumbHeight = thumbHeightPx,
        thumb = thumb,
        coerceThumbInTrack = coerceThumbInTrack,
        drawInactiveTrack = drawInactiveTrack,
        borderStroke = borderStroke,
        modifier = dragModifier
      )
    }
  }
}

internal fun stepsToTickFractions(steps: Int): List<Float> {
  return if (steps == 0) emptyList() else List(steps + 2) { it.toFloat() / (steps + 1) }
}

internal fun scale(start1: Float, end1: Float, pos: Float, start2: Float, end2: Float) =
  lerp(start2, end2, calculateFraction(start1, end1, pos))

fun calculateFraction(start: Float, end: Float, pos: Float) =
  (if (end - start == 0f) 0f else (pos - start) / (end - start)).coerceIn(0f, 1f)

internal fun lerp(start: Float, end: Float, amount: Float): Float {
  return (1 - amount) * start + amount * end
}
