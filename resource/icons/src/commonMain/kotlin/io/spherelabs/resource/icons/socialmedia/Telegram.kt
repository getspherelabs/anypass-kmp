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

public val SocialMediaIcons.Telegram: ImageVector
    get() {
        if (_telegram != null) {
            return _telegram!!
        }
        _telegram = Builder(name = "Telegram", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
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
                moveTo(389.059f, 161.936f)
                lineTo(343.591f, 379.0f)
                arcToRelative(16.007f, 16.007f, 0.0f, false, true, -25.177f, 9.593f)
                lineToRelative(-66.136f, -48.861f)
                lineToRelative(-40.068f, 37.8f)
                arcToRelative(5.429f, 5.429f, 0.0f, false, true, -7.74f, -0.294f)
                lineToRelative(-0.861f, -0.946f)
                lineToRelative(6.962f, -67.375f)
                lineTo(336.055f, 194.266f)
                arcToRelative(3.358f, 3.358f, 0.0f, false, false, -4.061f, -5.317f)
                lineTo(171.515f, 290.519f)
                lineTo(102.4f, 267.307f)
                arcToRelative(9.393f, 9.393f, 0.0f, false, true, -0.32f, -17.694f)
                lineTo(372.5f, 147.744f)
                arcTo(12.441f, 12.441f, 0.0f, false, true, 389.059f, 161.936f)
                close()
            }
        }
        .build()
        return _telegram!!
    }

private var _telegram: ImageVector? = null
