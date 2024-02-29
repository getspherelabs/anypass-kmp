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

public val SocialMediaIcons.Neftlix: ImageVector
    get() {
        if (_neftlix != null) {
            return _neftlix!!
        }
        _neftlix = Builder(name = "Neftlix", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(299.006f, 26.001f)
                lineToRelative(0.0f, 230.001f)
                lineToRelative(-86.02f, -230.001f)
                lineToRelative(-86.009f, 0.0f)
                lineToRelative(0.0f, 459.998f)
                lineToRelative(86.009f, 0.0f)
                lineToRelative(0.0f, -230.011f)
                lineToRelative(86.02f, 230.011f)
                lineToRelative(86.017f, 0.0f)
                lineToRelative(0.0f, -459.998f)
                close()
            }
        }
        .build()
        return _neftlix!!
    }

private var _neftlix: ImageVector? = null
