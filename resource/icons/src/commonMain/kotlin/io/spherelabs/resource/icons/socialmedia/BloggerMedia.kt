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

public val SocialMediaIcons.BloggerMedia: ImageVector
    get() {
        if (_BloggerMedia != null) {
            return _BloggerMedia!!
        }
        _BloggerMedia = Builder(name = "BloggerMedia",
                defaultWidth = 100.0.dp, defaultHeight = 100.0.dp, viewportWidth = 100.0f,
                viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(58.9f, 59.9f)
                horizontalLineToRelative(-21.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.1f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(21.0f)
                curveToRelative(1.1f, 0.0f, 2.1f, -0.9f, 2.1f, -2.0f)
                curveTo(61.0f, 60.9f, 60.1f, 59.9f, 58.9f, 59.9f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(37.9f, 40.0f)
                horizontalLineToRelative(11.4f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.1f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                horizontalLineTo(37.9f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                curveTo(36.0f, 39.1f, 36.9f, 40.0f, 37.9f, 40.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(85.0f, 4.0f)
                horizontalLineTo(15.0f)
                curveTo(8.9f, 4.0f, 4.0f, 8.9f, 4.0f, 15.0f)
                verticalLineToRelative(70.0f)
                curveToRelative(0.0f, 6.1f, 4.9f, 11.0f, 11.0f, 11.0f)
                horizontalLineToRelative(70.0f)
                curveToRelative(6.1f, 0.0f, 11.0f, -4.9f, 11.0f, -11.0f)
                verticalLineTo(15.0f)
                curveTo(96.0f, 8.9f, 91.1f, 4.0f, 85.0f, 4.0f)
                close()
                moveTo(81.0f, 62.0f)
                curveToRelative(0.0f, 10.5f, -8.5f, 19.0f, -19.0f, 19.0f)
                horizontalLineTo(37.9f)
                curveTo(27.5f, 81.0f, 19.0f, 72.5f, 19.0f, 62.0f)
                verticalLineTo(37.9f)
                curveTo(19.0f, 27.5f, 27.5f, 19.0f, 37.9f, 19.0f)
                horizontalLineToRelative(9.0f)
                curveToRelative(12.1f, 0.0f, 22.0f, 9.9f, 22.0f, 22.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.1f, 2.0f)
                horizontalLineToRelative(3.0f)
                curveToRelative(3.9f, 0.0f, 7.0f, 3.2f, 7.0f, 7.0f)
                verticalLineTo(62.0f)
                close()
            }
        }
        .build()
        return _BloggerMedia!!
    }

private var _BloggerMedia: ImageVector? = null
