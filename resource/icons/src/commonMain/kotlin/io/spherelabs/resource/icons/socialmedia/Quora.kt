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

public val SocialMediaIcons.Quora: ImageVector
    get() {
        if (_quora != null) {
            return _quora!!
        }
        _quora = Builder(name = "Quora", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(251.049f, 144.44f)
                curveToRelative(-40.614f, 0.0f, -68.558f, 24.318f, -68.558f, 100.794f)
                reflectiveCurveToRelative(27.944f, 100.795f, 68.558f, 100.795f)
                arcToRelative(75.426f, 75.426f, 0.0f, false, false, 23.915f, -3.53f)
                curveToRelative(-9.359f, -20.726f, -17.709f, -32.714f, -31.27f, -40.523f)
                reflectiveCurveToRelative(-29.454f, -0.685f, -29.454f, -0.685f)
                lineToRelative(-5.616f, -10.822f)
                curveToRelative(14.932f, -11.645f, 32.466f, -13.837f, 43.426f, -13.837f)
                curveToRelative(30.695f, 0.0f, 47.259f, 18.479f, 56.719f, 33.774f)
                curveToRelative(6.961f, -15.45f, 10.838f, -36.692f, 10.838f, -65.172f)
                curveTo(319.607f, 168.758f, 291.664f, 144.44f, 251.049f, 144.44f)
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
                moveTo(344.384f, 402.667f)
                curveToRelative(-31.455f, 0.0f, -48.743f, -20.431f, -59.453f, -39.612f)
                arcToRelative(122.654f, 122.654f, 0.0f, true, true, 41.723f, -21.343f)
                curveToRelative(8.241f, 11.847f, 19.651f, 23.418f, 33.21f, 23.418f)
                curveToRelative(20.549f, 0.0f, 23.289f, -20.412f, 23.289f, -20.412f)
                horizontalLineToRelative(13.7f)
                curveTo(396.853f, 368.555f, 388.085f, 402.667f, 344.384f, 402.667f)
                close()
            }
        }
        .build()
        return _quora!!
    }

private var _quora: ImageVector? = null
