package io.spherelabs.designsystem.meterprogress

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Jaguar

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun LKDashProgress(
    value: Int,
    modifier: Modifier = Modifier,
    valueFontWeight: FontWeight,
    valueFontFamily: FontFamily,
    valueColor: Color = Color.White,
) {
    Box(
        modifier =
        modifier.fillMaxSize().padding(105.dp).background(color = Jaguar, shape = CircleShape),
    ) {
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } + fadeIn() togetherWith
                        slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() togetherWith
                        slideOutVertically { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false),
                )
            },
            modifier = modifier.align(alignment = Alignment.Center)
        ) { targetCount ->
            Text(
                text = "$targetCount",
                fontSize = 42.sp,
                fontFamily = valueFontFamily,
                fontWeight = valueFontWeight,
                color = valueColor,
                // modifier = Modifier.align(alignment = Alignment.Center),
            )
        }

    }
}
