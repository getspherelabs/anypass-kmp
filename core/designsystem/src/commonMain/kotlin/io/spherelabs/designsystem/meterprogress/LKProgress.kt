package io.spherelabs.designsystem.meterprogress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LKOuterProgress(
    modifier: Modifier = Modifier
) {
    val nbMarker = 180


    Box(
        modifier
            .fillMaxWidth()
            .padding(40.dp)
    ) {
        for (i in 0 until nbMarker) {
            LKOuterMarker(
                angle = i * (360 / nbMarker),
                modifier =  modifier
            )
        }

    }
}

@Composable
fun LKInnerProgress(
    modifier: Modifier = Modifier
) {
    val nbMarker = 180

    Box(
        modifier
            .fillMaxWidth()
            .padding(85.dp)
    ) {
        for (i in 0 until nbMarker) {
            LKInnerMarker(
                angle = i * (360 / nbMarker)
            )
        }

    }
}

@Composable
fun LKCircleProgress(
    angle: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(95.dp)
            .drawCircleProgress(angle,color)
    )
}

private fun Modifier.drawCircleProgress(angle: Float, color: Color): Modifier {
    return drawBehind {
        drawArc(
            color = Color.Transparent,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = 20f)
        )
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = angle,
            useCenter = false,
            style = Stroke(width = 30f, cap = StrokeCap.Round)
        )
    }
}
