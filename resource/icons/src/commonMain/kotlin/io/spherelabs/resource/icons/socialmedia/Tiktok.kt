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

public val SocialMediaIcons.Tiktok: ImageVector
    get() {
        if (_tiktok != null) {
            return _tiktok!!
        }
        _tiktok = Builder(name = "Tiktok", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 0.0f)
                curveTo(114.615f, 0.0f, 0.0f, 114.615f, 0.0f, 256.0f)
                reflectiveCurveTo(114.615f, 512.0f, 256.0f, 512.0f)
                reflectiveCurveTo(512.0f, 397.385f, 512.0f, 256.0f)
                reflectiveCurveTo(397.385f, 0.0f, 256.0f, 0.0f)
                close()
                moveTo(385.62f, 232.382f)
                curveToRelative(-27.184f, 0.0f, -53.634f, -8.822f, -74.0f, -23.75f)
                lineToRelative(-0.162f, 101.5f)
                arcToRelative(92.457f, 92.457f, 0.0f, true, true, -80.178f, -91.721f)
                verticalLineToRelative(49.845f)
                arcToRelative(43.657f, 43.657f, 0.0f, true, false, 31.288f, 41.876f)
                verticalLineTo(109.333f)
                horizontalLineToRelative(51.275f)
                arcToRelative(71.773f, 71.773f, 0.0f, false, false, 71.774f, 71.773f)
                close()
            }
        }
        .build()
        return _tiktok!!
    }

private var _tiktok: ImageVector? = null
