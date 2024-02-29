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

public val SocialMediaIcons.Goodreads: ImageVector
    get() {
        if (_goodreads != null) {
            return _goodreads!!
        }
        _goodreads = Builder(name = "Goodreads", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(3.0f, 1.0f)
                curveTo(1.8954f, 1.0f, 1.0f, 1.8954f, 1.0f, 3.0f)
                verticalLineTo(21.0f)
                curveTo(1.0f, 22.1046f, 1.8954f, 23.0f, 3.0f, 23.0f)
                horizontalLineTo(21.0f)
                curveTo(22.1046f, 23.0f, 23.0f, 22.1046f, 23.0f, 21.0f)
                verticalLineTo(3.0f)
                curveTo(23.0f, 1.8954f, 22.1046f, 1.0f, 21.0f, 1.0f)
                horizontalLineTo(3.0f)
                close()
                moveTo(14.5f, 5.7578f)
                verticalLineTo(5.0f)
                horizontalLineTo(16.5f)
                verticalLineTo(9.5f)
                verticalLineTo(10.5f)
                verticalLineTo(15.75f)
                curveTo(16.5f, 18.0972f, 14.5972f, 20.0f, 12.25f, 20.0f)
                curveTo(9.9028f, 20.0f, 8.0f, 18.0972f, 8.0f, 15.75f)
                verticalLineTo(15.5f)
                horizontalLineTo(10.0f)
                verticalLineTo(15.75f)
                curveTo(10.0f, 16.9926f, 11.0074f, 18.0f, 12.25f, 18.0f)
                curveTo(13.4926f, 18.0f, 14.5f, 16.9926f, 14.5f, 15.75f)
                verticalLineTo(14.2422f)
                curveTo(13.785f, 14.7209f, 12.9251f, 15.0f, 12.0f, 15.0f)
                curveTo(9.5147f, 15.0f, 7.5f, 12.9853f, 7.5f, 10.5f)
                verticalLineTo(9.5f)
                curveTo(7.5f, 7.0147f, 9.5147f, 5.0f, 12.0f, 5.0f)
                curveTo(12.9251f, 5.0f, 13.785f, 5.2791f, 14.5f, 5.7578f)
                close()
                moveTo(14.5f, 9.5f)
                curveTo(14.5f, 8.1193f, 13.3807f, 7.0f, 12.0f, 7.0f)
                curveTo(10.6193f, 7.0f, 9.5f, 8.1193f, 9.5f, 9.5f)
                verticalLineTo(10.5f)
                curveTo(9.5f, 11.8807f, 10.6193f, 13.0f, 12.0f, 13.0f)
                curveTo(13.3807f, 13.0f, 14.5f, 11.8807f, 14.5f, 10.5f)
                verticalLineTo(9.5f)
                close()
            }
        }
        .build()
        return _goodreads!!
    }

private var _goodreads: ImageVector? = null
