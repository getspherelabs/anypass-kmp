package passwordhealthimpl.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Cinderella
import io.spherelabs.foundation.color.Jaguar


@Composable
fun PasswordHealthContent(
    modifier: Modifier = Modifier,
    percent: Float,
) {
    val animatedValue = remember { Animatable(0f) }
    var currentPercent = percent

    LaunchedEffect(animatedValue) {
        animatedValue.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        )

        currentPercent = percent * animatedValue.value
    }

    val textMeasurer = rememberTextMeasurer()

    val textToDraw = "$percent"

    val style = TextStyle(
        fontSize = 150.sp,
        color = Color.Black,
        background = Color.Red.copy(alpha = 0.2f),
    )

    val textLayoutResult = remember(textToDraw) {
        textMeasurer.measure(textToDraw, style)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier.size(400.dp).padding(16.dp)
                .drawProgress(currentPercent, textMeasurer, textToDraw, style, textLayoutResult),
        )
    }

}

internal fun Modifier.drawProgress(
    percent: Float, textMeasurer: TextMeasurer,
    textToDraw: String,
    style: TextStyle,
    textLayoutResult: TextLayoutResult,
): Modifier {
    return this.drawBehind {
        drawSemiProgress(percent, textMeasurer, textToDraw, style, textLayoutResult)
    }
}

private fun DrawScope.drawSemiProgress(
    percent: Float, textMeasurer: TextMeasurer,
    textToDraw: String,
    style: TextStyle,
    textLayoutResult: TextLayoutResult,
) {

    drawArc(
        color = Cinderella,
        startAngle = 180f,
        sweepAngle = 180f,
        useCenter = false,
        size = Size(900.dp.value, 900.dp.value),
        style = Stroke(width = 40f, cap = StrokeCap.Round),
    )
    drawArc(
        color = Color.Green,
        startAngle = 180f,
        sweepAngle = percent,
        useCenter = false,
        size = Size(900.dp.value, 900.dp.value),
        style = Stroke(width = 40f, cap = StrokeCap.Round),
    )

    drawText(
        textMeasurer = textMeasurer,
        text = textToDraw,
        style = style,
        topLeft = Offset(
            x = center.x - textLayoutResult.size.width / 2,
            y = center.y - textLayoutResult.size.height / 2,
        ),
    )
}

fun DrawScope.drawInnerText(
    textMeasurer: TextMeasurer,
    textToDraw: String,
    style: TextStyle,
    textLayoutResult: TextLayoutResult,
) {

}
