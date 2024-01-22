package io.spherelabs.resource.icons.socialmedia

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.SocialMediaIcons

public val SocialMediaIcons.Delicious: ImageVector
    get() {
        if (_delicious != null) {
            return _delicious!!
        }
        _delicious = Builder(name = "Delicious", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(1.0f, 2.0f)
                curveTo(1.0f, 1.4477f, 1.4477f, 1.0f, 2.0f, 1.0f)
                horizontalLineTo(22.0f)
                curveTo(22.5523f, 1.0f, 23.0f, 1.4477f, 23.0f, 2.0f)
                verticalLineTo(22.0f)
                curveTo(23.0f, 22.5523f, 22.5523f, 23.0f, 22.0f, 23.0f)
                horizontalLineTo(2.0f)
                curveTo(1.4477f, 23.0f, 1.0f, 22.5523f, 1.0f, 22.0f)
                verticalLineTo(2.0f)
                close()
                moveTo(3.0f, 3.0f)
                verticalLineTo(21.0f)
                horizontalLineTo(21.0f)
                verticalLineTo(3.0f)
                horizontalLineTo(3.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(2.0f, 11.0f)
                curveTo(1.4477f, 11.0f, 1.0f, 11.4477f, 1.0f, 12.0f)
                verticalLineTo(22.0f)
                curveTo(1.0f, 22.5523f, 1.4477f, 23.0f, 2.0f, 23.0f)
                horizontalLineTo(12.0f)
                curveTo(12.5523f, 23.0f, 13.0f, 22.5523f, 13.0f, 22.0f)
                verticalLineTo(12.0f)
                curveTo(13.0f, 11.4477f, 12.5523f, 11.0f, 12.0f, 11.0f)
                horizontalLineTo(2.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(12.0f, 1.0f)
                curveTo(11.4477f, 1.0f, 11.0f, 1.4477f, 11.0f, 2.0f)
                verticalLineTo(12.0f)
                curveTo(11.0f, 12.5523f, 11.4477f, 13.0f, 12.0f, 13.0f)
                horizontalLineTo(22.0f)
                curveTo(22.5523f, 13.0f, 23.0f, 12.5523f, 23.0f, 12.0f)
                verticalLineTo(2.0f)
                curveTo(23.0f, 1.4477f, 22.5523f, 1.0f, 22.0f, 1.0f)
                horizontalLineTo(12.0f)
                close()
            }
        }
        .build()
        return _delicious!!
    }

private var _delicious: ImageVector? = null
