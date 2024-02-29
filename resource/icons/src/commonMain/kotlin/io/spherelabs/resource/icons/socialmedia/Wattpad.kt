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

public val SocialMediaIcons.Wattpad: ImageVector
    get() {
        if (_wattpad != null) {
            return _wattpad!!
        }
        _wattpad = Builder(name = "Wattpad", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(370.996f, 68.62f)
                verticalLineToRelative(259.455f)
                horizontalLineToRelative(-57.498f)
                verticalLineTo(68.62f)
                horizontalLineTo(198.503f)
                verticalLineToRelative(259.455f)
                horizontalLineToRelative(-28.75f)
                curveToRelative(-15.844f, 0.0f, -28.752f, -12.918f, -28.752f, -28.832f)
                verticalLineTo(68.62f)
                horizontalLineToRelative(-115.0f)
                verticalLineToRelative(230.621f)
                curveToRelative(0.0f, 79.475f, 64.488f, 144.139f, 143.752f, 144.139f)
                horizontalLineToRelative(316.246f)
                verticalLineTo(68.62f)
                horizontalLineTo(370.996f)
                close()
            }
        }
        .build()
        return _wattpad!!
    }

private var _wattpad: ImageVector? = null
