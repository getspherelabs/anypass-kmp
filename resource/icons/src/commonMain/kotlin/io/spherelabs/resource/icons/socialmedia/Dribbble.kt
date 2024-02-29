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

public val SocialMediaIcons.Dribbble: ImageVector
    get() {
        if (_dribbble != null) {
            return _dribbble!!
        }
        _dribbble = Builder(name = "Dribbble", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(260.62f, 259.242f)
                quadToRelative(4.656f, -1.595f, 9.386f, -2.951f)
                quadToRelative(-4.878f, -11.262f, -9.943f, -21.634f)
                arcToRelative(463.064f, 463.064f, 0.0f, false, true, -123.789f, 17.01f)
                curveToRelative(-1.759f, 0.0f, -3.483f, -0.015f, -5.19f, -0.034f)
                curveToRelative(-0.05f, 1.45f, -0.084f, 2.9f, -0.084f, 4.367f)
                arcToRelative(124.521f, 124.521f, 0.0f, false, false, 31.827f, 83.318f)
                arcToRelative(287.891f, 287.891f, 0.0f, false, true, 21.967f, -27.609f)
                curveTo(207.905f, 286.215f, 233.416f, 268.564f, 260.62f, 259.242f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(272.858f, 208.275f)
                arcToRelative(164.678f, 164.678f, 0.0f, false, false, 66.288f, -45.6f)
                arcToRelative(125.147f, 125.147f, 0.0f, false, false, -112.632f, -28.166f)
                arcToRelative(582.655f, 582.655f, 0.0f, false, true, 32.743f, 49.44f)
                quadTo(266.381f, 195.986f, 272.858f, 208.275f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(267.535f, 279.424f)
                curveToRelative(-37.67f, 12.907f, -67.175f, 43.179f, -88.973f, 74.7f)
                arcTo(125.151f, 125.151f, 0.0f, false, false, 304.608f, 371.2f)
                arcToRelative(503.315f, 503.315f, 0.0f, false, false, -26.467f, -95.036f)
                curveTo(274.581f, 277.141f, 271.044f, 278.221f, 267.535f, 279.424f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(239.176f, 195.829f)
                arcToRelative(554.624f, 554.624f, 0.0f, false, false, -35.8f, -53.235f)
                arcToRelative(125.214f, 125.214f, 0.0f, false, false, -69.731f, 87.729f)
                arcToRelative(441.027f, 441.027f, 0.0f, false, false, 116.488f, -15.067f)
                curveTo(246.448f, 208.4f, 242.776f, 201.914f, 239.176f, 195.829f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(301.4f, 271.371f)
                quadToRelative(5.7f, 15.053f, 10.453f, 30.29f)
                arcToRelative(514.113f, 514.113f, 0.0f, false, true, 14.4f, 57.732f)
                arcToRelative(125.017f, 125.017f, 0.0f, false, false, 53.082f, -83.051f)
                curveTo(352.4f, 269.585f, 326.34f, 267.926f, 301.4f, 271.371f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 0.0f)
                curveTo(114.615f, 0.0f, 0.0f, 114.615f, 0.0f, 256.0f)
                reflectiveCurveTo(114.615f, 512.0f, 256.0f, 512.0f)
                reflectiveCurveTo(512.0f, 397.385f, 512.0f, 256.0f)
                reflectiveCurveTo(397.385f, 0.0f, 256.0f, 0.0f)
                close()
                moveTo(256.0f, 402.667f)
                arcTo(146.667f, 146.667f, 0.0f, true, true, 402.667f, 256.0f)
                arcTo(146.667f, 146.667f, 0.0f, false, true, 256.0f, 402.667f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(340.07f, 191.888f)
                arcToRelative(184.742f, 184.742f, 0.0f, false, true, -57.539f, 35.529f)
                quadToRelative(5.651f, 11.746f, 10.709f, 23.666f)
                arcToRelative(212.9f, 212.9f, 0.0f, false, true, 50.536f, -2.069f)
                arcToRelative(244.968f, 244.968f, 0.0f, false, true, 37.21f, 5.823f)
                arcToRelative(124.453f, 124.453f, 0.0f, false, false, -27.179f, -76.672f)
                quadTo(347.2f, 185.419f, 340.07f, 191.888f)
                close()
            }
        }
        .build()
        return _dribbble!!
    }

private var _dribbble: ImageVector? = null
