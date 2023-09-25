package io.spherelabs.designsystem.slider

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp

@Composable
internal fun LKBasicSlider(
  enabled: Boolean,
  fraction: Float,
  trackStart: Float,
  trackEnd: Float,
  tickFractions: List<Float>,
  colors: LKSliderColors,
  trackHeight: Dp,
  thumbRadius: Float,
  thumbHeight: Float,
  thumb: @Composable () -> Unit,
  coerceThumbInTrack: Boolean,
  drawInactiveTrack: Boolean,
  borderStroke: BorderStroke? = null,
  modifier: Modifier
) {

  val trackStrokeWidth: Float

  var borderWidth = 0f
  val borderBrush: Brush? = borderStroke?.brush
  val thumbHeightDp: Dp

  with(LocalDensity.current) {
    trackStrokeWidth = trackHeight.toPx()
    thumbHeightDp = (2 * thumbRadius.coerceAtLeast(thumbHeight)).toDp()

    if (borderStroke != null) {
      borderWidth = borderStroke.width.toPx()
    }
  }

  Box(
    modifier.heightIn(max = trackHeight.coerceAtLeast(thumbHeightDp).coerceAtLeast(4.dp)),
    contentAlignment = Alignment.CenterStart
  ) {
    val thumbCenterPos = (trackStart + (trackEnd - trackStart) * fraction)

    Track(
      modifier = Modifier.align(Alignment.CenterStart).fillMaxSize(),
      fraction = fraction,
      tickFractions = tickFractions,
      thumbRadius = thumbRadius,
      trackStart = trackStart,
      trackHeight = trackStrokeWidth,
      coerceThumbInTrack = coerceThumbInTrack,
      colors = colors,
      enabled = enabled,
      borderBrush = borderBrush,
      borderWidth = borderWidth,
      drawInactiveTrack = drawInactiveTrack
    )

    Box(modifier = modifier.offset { IntOffset((thumbCenterPos - thumbRadius).toInt(), 0) }) {
      thumb()
    }
  }
}
