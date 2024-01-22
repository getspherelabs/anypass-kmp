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

public val SocialMediaIcons.Imgur: ImageVector
    get() {
        if (_imgur != null) {
            return _imgur!!
        }
        _imgur = Builder(name = "Imgur", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(1.0f, 6.5f)
                curveTo(1.0f, 3.4624f, 3.4624f, 1.0f, 6.5f, 1.0f)
                horizontalLineTo(17.5f)
                curveTo(20.5376f, 1.0f, 23.0f, 3.4624f, 23.0f, 6.5f)
                verticalLineTo(17.5f)
                curveTo(23.0f, 20.5376f, 20.5376f, 23.0f, 17.5f, 23.0f)
                horizontalLineTo(6.5f)
                curveTo(3.4624f, 23.0f, 1.0f, 20.5376f, 1.0f, 17.5f)
                verticalLineTo(6.5f)
                close()
                moveTo(12.0f, 4.0f)
                curveTo(10.6193f, 4.0f, 9.5f, 5.1193f, 9.5f, 6.5f)
                curveTo(9.5f, 7.8807f, 10.6193f, 9.0f, 12.0f, 9.0f)
                curveTo(13.3807f, 9.0f, 14.5f, 7.8807f, 14.5f, 6.5f)
                curveTo(14.5f, 5.1193f, 13.3807f, 4.0f, 12.0f, 4.0f)
                close()
                moveTo(9.5f, 12.0f)
                curveTo(9.5f, 10.6193f, 10.6193f, 9.5f, 12.0f, 9.5f)
                curveTo(13.3807f, 9.5f, 14.5f, 10.6193f, 14.5f, 12.0f)
                verticalLineTo(17.5f)
                curveTo(14.5f, 18.8807f, 13.3807f, 20.0f, 12.0f, 20.0f)
                curveTo(10.6193f, 20.0f, 9.5f, 18.8807f, 9.5f, 17.5f)
                verticalLineTo(12.0f)
                close()
            }
        }
        .build()
        return _imgur!!
    }

private var _imgur: ImageVector? = null
