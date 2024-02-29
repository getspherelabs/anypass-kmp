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

public val SocialMediaIcons.Flickr: ImageVector
    get() {
        if (_flickr != null) {
            return _flickr!!
        }
        _flickr = Builder(name = "Flickr", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(436.714f, 26.001f)
                horizontalLineTo(75.287f)
                curveToRelative(-27.21f, 0.0f, -49.285f, 22.075f, -49.285f, 49.286f)
                verticalLineToRelative(361.427f)
                curveToRelative(0.0f, 27.211f, 22.075f, 49.285f, 49.285f, 49.285f)
                horizontalLineToRelative(361.427f)
                curveToRelative(27.211f, 0.0f, 49.284f, -22.074f, 49.284f, -49.285f)
                verticalLineTo(75.287f)
                curveTo(485.998f, 48.076f, 463.925f, 26.001f, 436.714f, 26.001f)
                close()
                moveTo(174.371f, 320.687f)
                curveToRelative(-36.041f, 0.0f, -65.2f, -29.16f, -65.2f, -65.199f)
                reflectiveCurveToRelative(29.159f, -65.2f, 65.2f, -65.2f)
                curveToRelative(36.039f, 0.0f, 65.201f, 29.161f, 65.201f, 65.2f)
                reflectiveCurveTo(210.41f, 320.687f, 174.371f, 320.687f)
                close()
                moveTo(337.63f, 320.687f)
                curveToRelative(-36.041f, 0.0f, -65.201f, -29.16f, -65.201f, -65.199f)
                reflectiveCurveToRelative(29.16f, -65.2f, 65.201f, -65.2f)
                curveToRelative(36.039f, 0.0f, 65.199f, 29.161f, 65.199f, 65.2f)
                reflectiveCurveTo(373.669f, 320.687f, 337.63f, 320.687f)
                close()
            }
        }
        .build()
        return _flickr!!
    }

private var _flickr: ImageVector? = null
