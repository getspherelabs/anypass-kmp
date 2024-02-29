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

public val SocialMediaIcons.Mix: ImageVector
    get() {
        if (_mix != null) {
            return _mix!!
        }
        _mix = Builder(name = "Mix", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp, viewportWidth
                = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(2.0f, 2.0f)
                curveTo(1.4477f, 2.0f, 1.0f, 2.4477f, 1.0f, 3.0f)
                verticalLineTo(7.0f)
                verticalLineTo(12.0f)
                verticalLineTo(19.0f)
                curveTo(1.0f, 20.6569f, 2.3431f, 22.0f, 4.0f, 22.0f)
                curveTo(5.6568f, 22.0f, 7.0f, 20.6569f, 7.0f, 19.0f)
                verticalLineTo(12.0f)
                verticalLineTo(9.0f)
                curveTo(7.0f, 8.4477f, 7.4477f, 8.0f, 8.0f, 8.0f)
                curveTo(8.5523f, 8.0f, 9.0f, 8.4477f, 9.0f, 9.0f)
                verticalLineTo(12.0f)
                verticalLineTo(15.0f)
                curveTo(9.0f, 16.6569f, 10.3431f, 18.0f, 12.0f, 18.0f)
                curveTo(13.6569f, 18.0f, 15.0f, 16.6569f, 15.0f, 15.0f)
                verticalLineTo(12.0f)
                verticalLineTo(11.0f)
                curveTo(15.0f, 10.4477f, 15.4477f, 10.0f, 16.0f, 10.0f)
                curveTo(16.5523f, 10.0f, 17.0f, 10.4477f, 17.0f, 11.0f)
                verticalLineTo(12.0f)
                curveTo(17.0f, 13.6569f, 18.3431f, 15.0f, 20.0f, 15.0f)
                curveTo(21.6569f, 15.0f, 23.0f, 13.6569f, 23.0f, 12.0f)
                verticalLineTo(7.0f)
                verticalLineTo(3.0f)
                curveTo(23.0f, 2.4477f, 22.5523f, 2.0f, 22.0f, 2.0f)
                horizontalLineTo(2.0f)
                close()
            }
        }
        .build()
        return _mix!!
    }

private var _mix: ImageVector? = null
