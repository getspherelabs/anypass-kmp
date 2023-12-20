package passwordhealthimpl.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun SemiProgressBar(
    modifier: Modifier = Modifier,
    currentProgress: Float,
    totalProgress: Float = SemiProgressBarTokens.totalProgress,
    size: Dp = SemiProgressBarTokens.size,
    thickness: Dp = SemiProgressBarTokens.thickness,
    startAngle: Float = SemiProgressBarTokens.starAngle,
    animationDuration: Int = SemiProgressBarTokens.animationDuration,
    style: SemiProgressBarStyle = SemiProgressBarDefaults.style(),
) {
    val foregroundColor: Color by style.foregroundColor()
    val backgroundColor: Color by style.backgroundColor()

    var currentProgressValue by remember {
        mutableStateOf(-1f)
    }

    val gapBetweenEnds = (startAngle - 90) * 2

    val currentAnimatingProgress = animateFloatAsState(
        targetValue = currentProgressValue,
        animationSpec = tween(
            durationMillis = animationDuration,
        ),
    )
    useEffect(true) {
        currentProgressValue = currentProgress
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size = size),
    ) {
        Canvas(
            modifier = Modifier
                .size(size = size),
        ) {

            drawArc(
                color = backgroundColor,
                startAngle = startAngle,
                sweepAngle = 360f - gapBetweenEnds,
                useCenter = false,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round),
            )

            val sweepAngle = currentAnimatingProgress.value.calculateSweepAngle(gapBetweenEnds)

            drawArc(
                color = foregroundColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Round),
            )
        }
        ShowCurrentProgress(currentAnimatingProgress)
    }

}

@Composable
internal fun ShowCurrentProgress(
    currentProgress: State<Float>,
    description: String = SemiProgressBarTokens.description,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = (currentProgress.value).toInt().toString(),
            style = TextStyle(
                fontFamily = GoogleSansFontFamily,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = LavenderBlue,
            ),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = description,
            style = TextStyle(
                fontFamily = GoogleSansFontFamily,
                fontSize = 16.sp,
                color = Color.White,
                ),
        )
    }
}

internal fun Float.calculateSweepAngle(gapBetweenEnds: Float): Float {
    return (this / SemiProgressBarTokens.maxTotalProgress) * (360F - gapBetweenEnds)
}
