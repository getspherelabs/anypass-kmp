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

public val SocialMediaIcons.Nextdoor: ImageVector
    get() {
        if (_nextdoor != null) {
            return _nextdoor!!
        }
        _nextdoor = Builder(name = "Nextdoor", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(2.5f, 3.5f)
                curveTo(1.9477f, 3.5f, 1.5f, 3.9477f, 1.5f, 4.5f)
                verticalLineTo(7.5f)
                curveTo(1.5f, 9.9853f, 3.5147f, 12.0f, 6.0f, 12.0f)
                horizontalLineTo(6.5f)
                verticalLineTo(19.5f)
                curveTo(6.5f, 20.0523f, 6.9477f, 20.5f, 7.5f, 20.5f)
                horizontalLineTo(11.5f)
                curveTo(12.0523f, 20.5f, 12.5f, 20.0523f, 12.5f, 19.5f)
                verticalLineTo(11.5f)
                curveTo(12.5f, 10.3954f, 13.3954f, 9.5f, 14.5f, 9.5f)
                curveTo(15.6046f, 9.5f, 16.5f, 10.3954f, 16.5f, 11.5f)
                verticalLineTo(19.5f)
                curveTo(16.5f, 20.0523f, 16.9477f, 20.5f, 17.5f, 20.5f)
                horizontalLineTo(21.5f)
                curveTo(22.0523f, 20.5f, 22.5f, 20.0523f, 22.5f, 19.5f)
                verticalLineTo(11.5f)
                curveTo(22.5f, 7.0817f, 18.9183f, 3.5f, 14.5f, 3.5f)
                curveTo(11.7541f, 3.5f, 9.3324f, 4.8839f, 7.8927f, 6.9885f)
                curveTo(7.6681f, 6.9394f, 7.5f, 6.7393f, 7.5f, 6.5f)
                verticalLineTo(4.5f)
                curveTo(7.5f, 3.9477f, 7.0523f, 3.5f, 6.5f, 3.5f)
                horizontalLineTo(2.5f)
                close()
            }
        }
        .build()
        return _nextdoor!!
    }

private var _nextdoor: ImageVector? = null
