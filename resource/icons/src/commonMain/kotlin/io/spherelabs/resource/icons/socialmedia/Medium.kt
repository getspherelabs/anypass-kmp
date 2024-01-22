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

public val SocialMediaIcons.`Medium`: ImageVector
    get() {
        if (`_Medium` != null) {
            return `_Medium`!!
        }
        `_Medium` = Builder(name = "Medium",
                defaultWidth = 512.0.dp, defaultHeight = 512.0.dp, viewportWidth = 512.0f,
                viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(449.446f, 0.0f)
                curveToRelative(34.525f, 0.0f, 62.554f, 28.03f, 62.554f, 62.554f)
                lineToRelative(0.0f, 386.892f)
                curveToRelative(0.0f, 34.524f, -28.03f, 62.554f, -62.554f, 62.554f)
                lineToRelative(-386.892f, 0.0f)
                curveToRelative(-34.524f, 0.0f, -62.554f, -28.03f, -62.554f, -62.554f)
                lineToRelative(0.0f, -386.892f)
                curveToRelative(0.0f, -34.524f, 28.029f, -62.554f, 62.554f, -62.554f)
                lineToRelative(386.892f, 0.0f)
                close()
                moveTo(429.944f, 137.321f)
                lineToRelative(-99.495f, 0.0f)
                lineToRelative(-70.926f, 176.926f)
                lineToRelative(-80.672f, -176.926f)
                lineToRelative(-104.37f, 0.0f)
                lineToRelative(0.0f, 6.049f)
                lineToRelative(33.614f, 40.493f)
                curveToRelative(3.301f, 2.974f, 4.985f, 7.343f, 4.537f, 11.762f)
                lineToRelative(0.0f, 159.114f)
                curveToRelative(0.978f, 5.739f, -0.83f, 11.603f, -4.872f, 15.794f)
                lineToRelative(-37.816f, 45.868f)
                lineToRelative(0.0f, 6.048f)
                lineToRelative(107.227f, 0.0f)
                lineToRelative(0.0f, -6.048f)
                lineToRelative(-37.816f, -45.868f)
                curveToRelative(-4.073f, -4.182f, -5.994f, -10.008f, -5.21f, -15.794f)
                lineToRelative(0.0f, -137.607f)
                lineToRelative(94.119f, 205.317f)
                lineToRelative(10.924f, 0.0f)
                lineToRelative(80.84f, -205.317f)
                lineToRelative(0.0f, 163.651f)
                curveToRelative(0.0f, 4.367f, 0.0f, 5.208f, -2.858f, 8.063f)
                lineToRelative(-29.075f, 28.229f)
                lineToRelative(0.0f, 6.048f)
                lineToRelative(141.175f, 0.0f)
                lineToRelative(0.0f, -6.048f)
                lineToRelative(-28.066f, -27.556f)
                curveToRelative(-2.478f, -1.888f, -3.707f, -4.993f, -3.194f, -8.065f)
                lineToRelative(0.0f, -202.463f)
                curveToRelative(-0.513f, -3.073f, 0.716f, -6.178f, 3.194f, -8.066f)
                lineToRelative(28.74f, -27.555f)
                lineToRelative(0.0f, -6.049f)
                close()
            }
        }
        .build()
        return `_Medium`!!
    }

private var `_Medium`: ImageVector? = null
