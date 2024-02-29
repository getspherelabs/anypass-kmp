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

public val SocialMediaIcons.Mixcloud: ImageVector
    get() {
        if (_mixcloud != null) {
            return _mixcloud!!
        }
        _mixcloud = Builder(name = "Mixcloud", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(19.33f, 9.93f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.16f, 0.69f)
                arcToRelative(5.44f, 5.44f, 0.0f, false, true, 0.0f, 5.77f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.85f, 0.53f)
                arcToRelative(6.44f, 6.44f, 0.0f, false, false, 0.0f, -6.82f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 19.33f, 9.93f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303c42)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(22.42f, 8.22f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, false, -0.83f, 0.55f)
                arcToRelative(8.58f, 8.58f, 0.0f, false, true, 0.0f, 9.45f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, false, 0.83f, 0.55f)
                arcToRelative(9.58f, 9.58f, 0.0f, false, false, 0.0f, -10.55f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303c42)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.27f, 10.78f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.31f, 0.41f)
                arcToRelative(6.52f, 6.52f, 0.0f, false, true, -0.42f, 1.73f)
                arcToRelative(0.51f, 0.51f, 0.0f, false, true, -0.65f, 0.28f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, -0.28f, -0.65f)
                arcToRelative(5.51f, 5.51f, 0.0f, false, false, -3.06f, -7.15f)
                lineToRelative(-0.06f, 0.0f)
                arcTo(5.45f, 5.45f, 0.0f, false, false, 5.0f, 7.42f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.29f, 0.77f)
                arcTo(4.91f, 4.91f, 0.0f, false, true, 7.54f, 9.46f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.0f, 0.71f)
                arcToRelative(0.51f, 0.51f, 0.0f, false, true, -0.71f, 0.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, false, 4.68f, 9.07f)
                horizontalLineToRelative(0.0f)
                arcTo(3.82f, 3.82f, 0.0f, false, false, 4.0f, 9.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, false, 0.0f, 8.0f)
                horizontalLineTo(14.5f)
                arcToRelative(3.49f, 3.49f, 0.0f, false, false, 2.28f, -6.14f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 16.27f, 10.78f)
                close()
            }
        }
        .build()
        return _mixcloud!!
    }

private var _mixcloud: ImageVector? = null
