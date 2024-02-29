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

public val SocialMediaIcons.Ello: ImageVector
    get() {
        if (_ello != null) {
            return _ello!!
        }
        _ello = Builder(name = "Ello", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 26.001f)
                curveToRelative(-127.03f, 0.0f, -230.0f, 102.971f, -230.0f, 229.999f)
                reflectiveCurveToRelative(102.97f, 229.999f, 230.0f, 229.999f)
                curveToRelative(127.027f, 0.0f, 229.999f, -102.971f, 229.999f, -229.999f)
                reflectiveCurveTo(383.027f, 26.001f, 256.0f, 26.001f)
                close()
                moveTo(389.399f, 290.499f)
                curveTo(374.068f, 351.069f, 318.869f, 394.0f, 256.0f, 394.0f)
                curveToRelative(-62.87f, 0.0f, -118.07f, -42.931f, -133.4f, -103.501f)
                curveToRelative(-1.531f, -6.899f, 2.299f, -14.568f, 9.2f, -16.099f)
                curveToRelative(6.899f, -1.53f, 14.57f, 2.301f, 16.1f, 9.199f)
                curveToRelative(13.031f, 49.069f, 57.499f, 83.57f, 108.1f, 83.57f)
                curveToRelative(50.598f, 0.0f, 95.069f, -34.501f, 108.099f, -83.57f)
                curveToRelative(1.53f, -6.898f, 9.2f, -11.501f, 16.1f, -9.199f)
                curveTo(387.1f, 275.931f, 391.699f, 283.6f, 389.399f, 290.499f)
                close()
            }
        }
        .build()
        return _ello!!
    }

private var _ello: ImageVector? = null
