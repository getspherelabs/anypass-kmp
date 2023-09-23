package io.spherelabs.designsystem.image


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale


@Composable
expect fun RoundedImage(
    painter: Painter,
    imageSize: Int = 48,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment= Alignment.Center,
    contentScale: ContentScale= ContentScale.Fit ,
    alpha: Float= DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality
)