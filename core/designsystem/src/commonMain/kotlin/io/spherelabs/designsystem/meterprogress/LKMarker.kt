package io.spherelabs.designsystem.meterprogress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun LKOuterMarker(
    angle: Int,
    color: Color = LKMeterProgressTokens.OuterMarkerColor,
    modifier: Modifier = Modifier
) {
  Box(modifier.fillMaxSize().drawOuterMarker(angle, color))
}

@Composable
internal fun LKInnerMarker(
    angle: Int,
    color: Color = LKMeterProgressTokens.InnerMarkerColor,
    modifier: Modifier = Modifier
) {
  Box(modifier.fillMaxSize().drawInnerMarker(angle, color))
}

internal fun Modifier.drawInnerMarker(angle: Int, color: Color): Modifier {
  return this.drawBehind {
    val theta = (angle - 90) * PI.toFloat() / 180f
    val startRadius = size.width / 2 * .65f
    val endRadius = size.width / 2 * .8f
    val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
    val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
    drawMarkerLine(color, startPos, endPos, 2f)
  }
}

internal fun Modifier.drawOuterMarker(angle: Int, color: Color): Modifier {
  return this.drawBehind {
    val theta = (angle - 90) * PI.toFloat() / 180f
    val startRadius = size.width / 2 * .69f
    val endRadius = size.width / 2 * .8f
    val startPos = calculateStartPos(theta, startRadius, endRadius)
    val endPos = calculateEndPos(theta, startRadius, endRadius)
    drawMarkerLine(color, startPos, endPos)
  }
}

private fun DrawScope.drawMarkerLine(
    color: Color,
    start: Offset,
    end: Offset,
    strokeWidth: Float = 4f
) {
  drawLine(
      color = color,
      start = center + start,
      end = center + end,
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round)
}

private fun calculateStartPos(theta: Float, startRadius: Float, endRadius: Float): Offset {
  return Offset(cos(theta) * startRadius, sin(theta) * startRadius)
}

private fun calculateEndPos(theta: Float, startRadius: Float, endRadius: Float): Offset {
  return Offset(cos(theta) * endRadius, sin(theta) * endRadius)
}
