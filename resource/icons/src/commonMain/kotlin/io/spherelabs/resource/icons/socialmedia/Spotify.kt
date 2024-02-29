package io.spherelabs.resource.icons.socialmedia

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.SocialMediaIcons

public val SocialMediaIcons.Spotify: ImageVector
    get() {
        if (_Spotify != null) {
            return _Spotify!!
        }
        _Spotify = Builder(name = "Spotify",
                defaultWidth = 100.0.dp, defaultHeight = 100.0.dp, viewportWidth = 100.0f,
                viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(50.0f, 4.0f)
                curveTo(24.7f, 4.0f, 4.0f, 24.7f, 4.0f, 50.0f)
                reflectiveCurveToRelative(20.6f, 46.0f, 46.0f, 46.0f)
                reflectiveCurveToRelative(46.0f, -20.6f, 46.0f, -46.0f)
                reflectiveCurveTo(75.4f, 4.0f, 50.0f, 4.0f)
                close()
                moveTo(71.6f, 71.5f)
                curveToRelative(0.0f, 0.0f, 0.0f, 0.1f, -0.1f, 0.1f)
                curveToRelative(-0.8f, 1.2f, -2.0f, 1.8f, -3.2f, 1.8f)
                curveToRelative(-0.7f, 0.0f, -1.4f, -0.2f, -2.0f, -0.6f)
                curveToRelative(-10.2f, -6.3f, -23.3f, -7.7f, -38.8f, -4.1f)
                curveToRelative(-2.1f, 0.6f, -4.0f, -0.9f, -4.5f, -2.7f)
                curveToRelative(-0.6f, -2.3f, 0.9f, -4.1f, 2.7f, -4.6f)
                curveToRelative(17.7f, -4.0f, 32.6f, -2.3f, 44.4f, 5.0f)
                curveToRelative(0.9f, 0.4f, 1.5f, 1.0f, 1.8f, 1.9f)
                curveTo(72.2f, 69.3f, 72.1f, 70.5f, 71.6f, 71.5f)
                close()
                moveTo(76.9f, 59.3f)
                lineTo(76.9f, 59.3f)
                curveToRelative(-0.8f, 1.1f, -1.9f, 1.9f, -3.2f, 2.1f)
                curveToRelative(-0.2f, 0.0f, -0.5f, 0.1f, -0.7f, 0.1f)
                curveToRelative(-0.8f, 0.0f, -1.6f, -0.3f, -2.3f, -0.7f)
                curveToRelative(-12.0f, -7.3f, -30.1f, -9.4f, -43.9f, -5.0f)
                curveToRelative(-2.5f, 0.6f, -5.0f, -0.7f, -5.6f, -3.0f)
                curveToRelative(-0.6f, -2.5f, 0.7f, -4.9f, 3.0f, -5.5f)
                curveToRelative(16.5f, -5.0f, 37.2f, -2.5f, 51.4f, 6.2f)
                curveToRelative(0.8f, 0.4f, 1.5f, 1.3f, 1.8f, 2.5f)
                curveTo(77.9f, 57.0f, 77.6f, 58.3f, 76.9f, 59.3f)
                close()
                moveTo(83.2f, 45.6f)
                curveToRelative(-1.0f, 1.4f, -2.7f, 2.1f, -4.4f, 2.1f)
                curveToRelative(-0.9f, 0.0f, -1.9f, -0.2f, -2.7f, -0.7f)
                curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
                curveToRelative(-13.9f, -8.3f, -37.8f, -9.3f, -51.4f, -5.1f)
                curveToRelative(-2.7f, 0.8f, -5.5f, -0.7f, -6.4f, -3.3f)
                curveToRelative(-0.8f, -2.7f, 0.7f, -5.6f, 3.3f, -6.4f)
                curveToRelative(16.2f, -4.8f, 43.0f, -3.8f, 59.8f, 6.2f)
                curveTo(83.8f, 39.6f, 84.7f, 42.9f, 83.2f, 45.6f)
                curveTo(83.3f, 45.5f, 83.3f, 45.5f, 83.2f, 45.6f)
                close()
            }
        }
        .build()
        return _Spotify!!
    }

private var _Spotify: ImageVector? = null
