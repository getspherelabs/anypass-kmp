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

public val SocialMediaIcons.`Pinterest`: ImageVector
    get() {
        if (`_Pinterest` != null) {
            return `_Pinterest`!!
        }
        `_Pinterest` = Builder(name =
                "Pinterest", defaultWidth = 512.0.dp,
                defaultHeight = 512.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(255.998f, 0.001f)
                curveToRelative(-141.384f, 0.0f, -255.998f, 114.617f, -255.998f, 255.998f)
                curveToRelative(0.0f, 108.456f, 67.475f, 201.171f, 162.707f, 238.471f)
                curveToRelative(-2.24f, -20.255f, -4.261f, -51.405f, 0.889f, -73.518f)
                curveToRelative(4.65f, -19.978f, 30.018f, -127.248f, 30.018f, -127.248f)
                curveToRelative(0.0f, 0.0f, -7.659f, -15.334f, -7.659f, -38.008f)
                curveToRelative(0.0f, -35.596f, 20.632f, -62.171f, 46.323f, -62.171f)
                curveToRelative(21.839f, 0.0f, 32.391f, 16.399f, 32.391f, 36.061f)
                curveToRelative(0.0f, 21.966f, -13.984f, 54.803f, -21.203f, 85.235f)
                curveToRelative(-6.03f, 25.482f, 12.779f, 46.261f, 37.909f, 46.261f)
                curveToRelative(45.503f, 0.0f, 80.477f, -47.976f, 80.477f, -117.229f)
                curveToRelative(0.0f, -61.293f, -44.045f, -104.149f, -106.932f, -104.149f)
                curveToRelative(-72.841f, 0.0f, -115.597f, 54.634f, -115.597f, 111.095f)
                curveToRelative(0.0f, 22.004f, 8.475f, 45.596f, 19.052f, 58.421f)
                curveToRelative(2.09f, 2.535f, 2.398f, 4.758f, 1.776f, 7.343f)
                curveToRelative(-1.945f, 8.087f, -6.262f, 25.474f, -7.111f, 29.032f)
                curveToRelative(-1.117f, 4.686f, -3.711f, 5.681f, -8.561f, 3.424f)
                curveToRelative(-31.974f, -14.884f, -51.963f, -61.627f, -51.963f, -99.174f)
                curveToRelative(0.0f, -80.755f, 58.672f, -154.915f, 169.148f, -154.915f)
                curveToRelative(88.806f, 0.0f, 157.821f, 63.279f, 157.821f, 147.85f)
                curveToRelative(0.0f, 88.229f, -55.629f, 159.232f, -132.842f, 159.232f)
                curveToRelative(-25.94f, 0.0f, -50.328f, -13.476f, -58.674f, -29.394f)
                curveToRelative(0.0f, 0.0f, -12.838f, 48.878f, -15.95f, 60.856f)
                curveToRelative(-5.782f, 22.237f, -21.382f, 50.109f, -31.818f, 67.11f)
                curveToRelative(23.955f, 7.417f, 49.409f, 11.416f, 75.797f, 11.416f)
                curveToRelative(141.389f, 0.0f, 256.003f, -114.612f, 256.003f, -256.001f)
                curveToRelative(0.0f, -141.381f, -114.614f, -255.998f, -256.003f, -255.998f)
                close()
            }
        }
        .build()
        return `_Pinterest`!!
    }

private var `_Pinterest`: ImageVector? = null
