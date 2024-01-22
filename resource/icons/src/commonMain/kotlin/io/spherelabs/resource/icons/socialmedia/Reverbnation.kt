package io.spherelabs.resource.icons.socialmedia

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.SocialMediaIcons

public val SocialMediaIcons.Reverbnation: ImageVector
    get() {
        if (_reverbnation != null) {
            return _reverbnation!!
        }
        _reverbnation = Builder(name = "Reverbnation", defaultWidth = 512.0.dp, defaultHeight =
                512.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(0.0f, 0.0f)
                lineToRelative(435.199f, 0.0f)
                lineToRelative(76.801f, 76.801f)
                lineToRelative(0.0f, 435.199f)
                lineToRelative(-435.201f, 0.0f)
                lineToRelative(-76.799f, -76.801f)
                close()
            }
            path(fill = SolidColor(Color(0xFF424242)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(435.199f, 0.0f)
                lineToRelative(76.801f, 76.801f)
                lineToRelative(-76.801f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF010101)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(435.199f, 76.801f)
                lineToRelative(76.801f, 76.801f)
                lineToRelative(0.0f, -76.801f)
                close()
            }
            path(fill = SolidColor(Color(0xFF424242)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(76.799f, 512.0f)
                lineToRelative(-76.799f, -76.801f)
                lineToRelative(76.799f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF010101)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(153.598f, 512.0f)
                lineToRelative(-76.799f, -76.801f)
                lineToRelative(0.0f, 76.801f)
                close()
            }
            path(fill = SolidColor(Color(0xFF010101)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(231.809f, 358.805f)
                lineToRelative(-79.621f, 57.852f)
                lineTo(247.533f, 512.0f)
                horizontalLineToRelative(137.475f)
                horizontalLineTo(512.0f)
                verticalLineTo(339.652f)
                verticalLineToRelative(-47.367f)
                lineToRelative(-79.426f, -79.422f)
                lineToRelative(-27.41f, 19.959f)
                lineToRelative(-79.82f, 57.85f)
                lineToRelative(41.117f, 125.984f)
                lineToRelative(-107.037f, -78.012f)
                lineTo(231.809f, 358.805f)
                close()
                moveTo(259.424f, 87.084f)
                lineToRelative(40.717f, 126.184f)
                lineToRelative(85.27f, -0.203f)
                lineTo(259.424f, 87.084f)
                close()
                moveTo(86.072f, 212.863f)
                lineToRelative(99.98f, 100.18f)
                lineToRelative(7.461f, -22.371f)
                lineTo(86.072f, 212.863f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE02A2B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(259.424f, 87.084f)
                lineToRelative(40.717f, 126.184f)
                lineToRelative(132.433f, -0.405f)
                lineToRelative(-107.23f, 77.809f)
                lineToRelative(41.117f, 125.984f)
                lineToRelative(-107.037f, -78.011f)
                lineToRelative(-107.236f, 78.011f)
                lineToRelative(41.326f, -125.984f)
                lineToRelative(-107.442f, -77.809f)
                lineToRelative(132.436f, 0.405f)
                close()
            }
        }
        .build()
        return _reverbnation!!
    }

private var _reverbnation: ImageVector? = null
