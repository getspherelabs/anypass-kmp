package io.spherelabs.designsystem.slider

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.LayoutDirection

@Composable
internal fun Track(
    modifier: Modifier,
    fraction: Float,
    tickFractions: List<Float>,
    thumbRadius: Float,
    trackStart: Float,
    trackHeight: Float,
    coerceThumbInTrack: Boolean,
    colors: LKSliderColors = LKSliderDefaults.sliderColors(),
    enabled: Boolean,
    borderBrush: Brush?,
    borderWidth: Float,
    drawInactiveTrack: Boolean,
) {

    val debug = false

    val activeTrackColor: Color by colors.trackColor(enabled)
    val inactiveTrackColor: Color = colors.trackColor(enabled = enabled).value
    val inactiveTickColor = colors.tickColor(enabled).value
    val activeTickColor by colors.tickColor(enabled)

    val strokeRadius = trackHeight / 2

    val drawStart = if (coerceThumbInTrack) trackStart - thumbRadius + strokeRadius else trackStart

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val isRtl = layoutDirection == LayoutDirection.Rtl

        val centerY = center.y

        val sliderLeft = Offset(drawStart, centerY)
        val sliderRight = Offset((width - drawStart).coerceAtLeast(drawStart), centerY)

        val sliderStart = if (isRtl) sliderRight else sliderLeft
        val sliderEnd = if (isRtl) sliderLeft else sliderRight

        val sliderValue = Offset(sliderStart.x + (sliderEnd.x - sliderStart.x) * fraction, center.y)

        // InActive Track
        drawLine(
            color = inactiveTrackColor,
            start = sliderStart,
            end = sliderEnd,
            strokeWidth = trackHeight,
            cap = StrokeCap.Round,
        )

        // Active Track
        drawLine(
            color = activeTrackColor,
            start = sliderStart,
            end = if (drawInactiveTrack) sliderValue else sliderEnd,
            strokeWidth = trackHeight,
            cap = StrokeCap.Round,
        )

        if (debug) {
            drawLine(
                color = Color.Yellow,
                start = sliderStart,
                end = sliderEnd,
                strokeWidth = strokeRadius / 4,
            )
        }

        borderBrush?.let { brush ->
            drawRoundRect(
                brush = brush,
                topLeft = Offset(sliderStart.x - strokeRadius, (height - trackHeight) / 2),
                size = Size(width = sliderEnd.x - sliderStart.x + trackHeight, trackHeight),
                cornerRadius = CornerRadius(strokeRadius, strokeRadius),
                style = Stroke(width = borderWidth),
            )
        }

        if (drawInactiveTrack) {
            tickFractions
                .groupBy { it > fraction }
                .forEach { (outsideFraction, list) ->
                    drawPoints(
                        points = list.map { Offset(lerp(sliderStart, sliderEnd, it).x, center.y) },
                        pointMode = PointMode.Points,
                        color = if (outsideFraction) activeTickColor else activeTickColor,
                        strokeWidth = strokeRadius.coerceAtMost(thumbRadius / 2),
                        cap = StrokeCap.Round,
                    )
                }
        }
    }
}
