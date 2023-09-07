package io.spherelabs.lockerkmp.components.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import io.spherelabs.lockerkmp.ui.createpassword.purpleLight


@Composable
fun OuterProgress(
    modifier: Modifier = Modifier
) {
    val nbMarker = 180

    val markerActives by animateFloatAsState(
        targetValue = nbMarker / 30f * 60,
        animationSpec = tween(500)
    )

    Box(
        modifier
            .fillMaxWidth()
            .padding(40.dp)
    ) {
        for (i in 0 until nbMarker) {
            OuterMarker(
                angle = i * (360 / nbMarker),
            )
        }

    }
}

@Composable
fun InnerProgress(
    modifier: Modifier = Modifier
) {
    val nbMarker = 180

    Box(
        modifier
            .fillMaxWidth()
            .padding(85.dp)
    ) {
        for (i in 0 until nbMarker) {
            InnerMarker(
                angle = i * (360 / nbMarker)
            )
        }

    }
}

@Composable
fun CircleProgress(
    angle: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(95.dp)
            .drawCircleProgress(angle)
    )
}

private fun Modifier.drawCircleProgress(angle: Float): Modifier {
    return drawBehind {
        drawArc(
            color = Color.Transparent,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = 20f)
        )
        drawArc(
            color = purpleLight,
            startAngle = -90f,
            sweepAngle = angle,
            useCenter = false,
            style = Stroke(width = 30f, cap = StrokeCap.Round)
        )
    }
}