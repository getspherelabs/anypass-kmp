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

public val SocialMediaIcons.Yubo: ImageVector
    get() {
        if (_yubo != null) {
            return _yubo!!
        }
        _yubo = Builder(name = "Yubo", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(1.0f, 5.0f)
                curveTo(1.0f, 2.7909f, 2.7909f, 1.0f, 5.0f, 1.0f)
                horizontalLineTo(19.0f)
                curveTo(21.2091f, 1.0f, 23.0f, 2.7909f, 23.0f, 5.0f)
                verticalLineTo(19.0f)
                curveTo(23.0f, 21.2091f, 21.2091f, 23.0f, 19.0f, 23.0f)
                horizontalLineTo(5.0f)
                curveTo(2.7909f, 23.0f, 1.0f, 21.2091f, 1.0f, 19.0f)
                verticalLineTo(5.0f)
                close()
                moveTo(16.8569f, 11.3502f)
                curveTo(17.3759f, 11.1613f, 17.9498f, 11.4289f, 18.1386f, 11.9479f)
                curveTo(18.4081f, 12.6883f, 18.5292f, 13.4745f, 18.4948f, 14.2617f)
                curveTo(18.4604f, 15.0489f, 18.2713f, 15.8216f, 17.9383f, 16.5357f)
                curveTo(17.6053f, 17.2498f, 17.135f, 17.8913f, 16.554f, 18.4236f)
                curveTo(15.9731f, 18.956f, 15.293f, 19.3687f, 14.5526f, 19.6381f)
                curveTo(13.8122f, 19.9076f, 13.026f, 20.0286f, 12.2388f, 19.9943f)
                curveTo(11.4516f, 19.9599f, 10.6789f, 19.7708f, 9.9648f, 19.4378f)
                curveTo(9.2507f, 19.1048f, 8.6091f, 18.6344f, 8.0768f, 18.0535f)
                curveTo(7.5445f, 17.4726f, 7.1318f, 16.7925f, 6.8623f, 16.0521f)
                curveTo(6.6734f, 15.5331f, 6.941f, 14.9593f, 7.46f, 14.7704f)
                lineTo(16.8569f, 11.3502f)
                close()
            }
        }
        .build()
        return _yubo!!
    }

private var _yubo: ImageVector? = null
