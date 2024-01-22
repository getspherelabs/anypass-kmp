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

public val SocialMediaIcons.Foursquare: ImageVector
    get() {
        if (_foursquare != null) {
            return _foursquare!!
        }
        _foursquare = Builder(name = "Foursquare", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.64f, 0.57f)
                arcTo(1.58f, 1.58f, 0.0f, false, false, 19.42f, 0.0f)
                horizontalLineTo(5.65f)
                arcTo(2.62f, 2.62f, 0.0f, false, false, 3.0f, 2.59f)
                verticalLineTo(22.06f)
                arcTo(2.0f, 2.0f, 0.0f, false, false, 5.0f, 24.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.48f, -0.65f)
                lineTo(13.22f, 16.0f)
                horizontalLineTo(16.0f)
                arcToRelative(2.63f, 2.63f, 0.0f, false, false, 2.61f, -2.13f)
                lineToRelative(1.0f, -5.28f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 19.18f, 8.0f)
                horizontalLineTo(11.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.0f, -1.0f)
                horizontalLineToRelative(8.06f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.49f, -0.41f)
                lineTo(21.0f, 1.82f)
                arcTo(1.52f, 1.52f, 0.0f, false, false, 20.64f, 0.57f)
                close()
            }
        }
        .build()
        return _foursquare!!
    }

private var _foursquare: ImageVector? = null
