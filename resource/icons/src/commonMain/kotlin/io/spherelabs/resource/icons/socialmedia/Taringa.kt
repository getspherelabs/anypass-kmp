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

public val SocialMediaIcons.Taringa: ImageVector
    get() {
        if (_taringa != null) {
            return _taringa!!
        }
        _taringa = Builder(name = "Taringa", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(5.0f, 1.0f)
                curveTo(2.7909f, 1.0f, 1.0f, 2.7909f, 1.0f, 5.0f)
                verticalLineTo(19.0f)
                curveTo(1.0f, 21.2091f, 2.7909f, 23.0f, 5.0f, 23.0f)
                horizontalLineTo(19.0f)
                curveTo(21.2091f, 23.0f, 23.0f, 21.2091f, 23.0f, 19.0f)
                verticalLineTo(5.0f)
                curveTo(23.0f, 2.7909f, 21.2091f, 1.0f, 19.0f, 1.0f)
                horizontalLineTo(5.0f)
                close()
                moveTo(5.0f, 6.0f)
                curveTo(4.4477f, 6.0f, 4.0f, 6.4477f, 4.0f, 7.0f)
                verticalLineTo(10.0f)
                curveTo(4.0f, 10.5523f, 4.4477f, 11.0f, 5.0f, 11.0f)
                horizontalLineTo(6.5f)
                verticalLineTo(18.0f)
                curveTo(6.5f, 18.5523f, 6.9477f, 19.0f, 7.5f, 19.0f)
                horizontalLineTo(10.5f)
                curveTo(11.0523f, 19.0f, 11.5f, 18.5523f, 11.5f, 18.0f)
                verticalLineTo(11.0f)
                horizontalLineTo(13.0f)
                curveTo(13.5523f, 11.0f, 14.0f, 10.5523f, 14.0f, 10.0f)
                verticalLineTo(7.0f)
                curveTo(14.0f, 6.4477f, 13.5523f, 6.0f, 13.0f, 6.0f)
                horizontalLineTo(5.0f)
                close()
                moveTo(15.0f, 7.0f)
                curveTo(15.0f, 6.4477f, 15.4477f, 6.0f, 16.0f, 6.0f)
                horizontalLineTo(19.0f)
                curveTo(19.5523f, 6.0f, 20.0f, 6.4477f, 20.0f, 7.0f)
                verticalLineTo(13.0f)
                curveTo(20.0f, 13.5523f, 19.5523f, 14.0f, 19.0f, 14.0f)
                horizontalLineTo(16.0f)
                curveTo(15.4477f, 14.0f, 15.0f, 13.5523f, 15.0f, 13.0f)
                verticalLineTo(7.0f)
                close()
                moveTo(16.0f, 14.5f)
                curveTo(15.4477f, 14.5f, 15.0f, 14.9477f, 15.0f, 15.5f)
                verticalLineTo(18.0f)
                curveTo(15.0f, 18.5523f, 15.4477f, 19.0f, 16.0f, 19.0f)
                horizontalLineTo(19.0f)
                curveTo(19.5523f, 19.0f, 20.0f, 18.5523f, 20.0f, 18.0f)
                verticalLineTo(15.5f)
                curveTo(20.0f, 14.9477f, 19.5523f, 14.5f, 19.0f, 14.5f)
                horizontalLineTo(16.0f)
                close()
            }
        }
        .build()
        return _taringa!!
    }

private var _taringa: ImageVector? = null
