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

public val SocialMediaIcons.Default: ImageVector
    get() {
        if (_default != null) {
            return _default!!
        }
        _default = Builder(name = "Default", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.2123f, 2.0f)
                curveTo(6.738f, 2.0f, 6.3211f, 2.3145f, 6.1908f, 2.7705f)
                lineTo(3.9412f, 10.6442f)
                curveTo(3.7473f, 11.3228f, 4.2569f, 11.9984f, 4.9627f, 11.9984f)
                horizontalLineTo(6.23f)
                lineTo(5.0603f, 16.6773f)
                curveTo(4.7964f, 17.7329f, 6.1011f, 18.4551f, 6.8555f, 17.6726f)
                lineTo(15.532f, 8.8151f)
                lineTo(15.5356f, 8.8114f)
                curveTo(16.1764f, 8.1436f, 15.7155f, 7.0f, 14.7691f, 7.0f)
                horizontalLineTo(12.2053f)
                lineTo(13.4667f, 3.4058f)
                lineTo(13.4693f, 3.3984f)
                curveTo(13.6986f, 2.7104f, 13.1865f, 2.0f, 12.4614f, 2.0f)
                horizontalLineTo(7.2123f)
                close()
            }
        }
        .build()
        return _default!!
    }

private var _default: ImageVector? = null
