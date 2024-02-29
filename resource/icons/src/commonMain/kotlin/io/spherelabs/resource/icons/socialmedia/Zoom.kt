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

public val SocialMediaIcons.Zoom: ImageVector
    get() {
        if (_zoom != null) {
            return _zoom!!
        }
        _zoom = Builder(name = "Zoom", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 0.0f)
                curveToRelative(141.39f, 0.0f, 256.0f, 114.61f, 256.0f, 256.0f)
                curveToRelative(0.0f, 141.39f, -114.61f, 256.0f, -256.0f, 256.0f)
                curveToRelative(-141.39f, 0.0f, -256.0f, -114.61f, -256.0f, -256.0f)
                curveToRelative(0.0f, -141.39f, 114.61f, -256.0f, 256.0f, -256.0f)
                close()
                moveTo(131.3f, 195.15f)
                lineToRelative(0.0f, 91.33f)
                curveToRelative(0.08f, 20.66f, 16.95f, 37.28f, 37.52f, 37.19f)
                lineToRelative(133.14f, 0.0f)
                curveToRelative(3.78f, 0.0f, 6.82f, -3.04f, 6.82f, -6.74f)
                lineToRelative(0.0f, -91.34f)
                curveToRelative(-0.08f, -20.65f, -16.95f, -37.27f, -37.52f, -37.19f)
                lineToRelative(-133.13f, 0.0f)
                curveToRelative(-3.79f, 0.0f, -6.83f, 3.05f, -6.83f, 6.75f)
                close()
                moveTo(317.26f, 230.78f)
                lineToRelative(54.97f, -40.16f)
                curveToRelative(4.77f, -3.95f, 8.47f, -2.96f, 8.47f, 4.2f)
                lineToRelative(0.0f, 122.44f)
                curveToRelative(0.0f, 8.14f, -4.52f, 7.16f, -8.47f, 4.19f)
                lineToRelative(-54.97f, -40.07f)
                lineToRelative(0.0f, -50.6f)
                close()
            }
        }
        .build()
        return _zoom!!
    }

private var _zoom: ImageVector? = null
