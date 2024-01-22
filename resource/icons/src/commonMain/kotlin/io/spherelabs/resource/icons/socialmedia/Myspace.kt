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

public val SocialMediaIcons.Myspace: ImageVector
    get() {
        if (_myspace != null) {
            return _myspace!!
        }
        _myspace = Builder(name = "Myspace", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(256.23f, 512.0f)
                curveTo(396.81f, 512.0f, 512.0f, 396.81f, 512.0f, 256.23f)
                curveTo(512.0f, 115.184f, 396.81f, 0.0f, 256.23f, 0.0f)
                curveTo(115.184f, 0.0f, 0.0f, 115.184f, 0.0f, 256.23f)
                curveTo(0.0f, 396.81f, 115.184f, 512.0f, 256.23f, 512.0f)
                lineTo(256.23f, 512.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(415.854f, 351.908f)
                horizontalLineToRelative(-112.02f)
                verticalLineToRelative(-24.029f)
                curveToRelative(0.0f, -30.84f, 25.403f, -55.791f, 56.244f, -55.791f)
                curveToRelative(30.824f, 0.0f, 55.775f, 24.951f, 55.775f, 55.791f)
                verticalLineTo(351.908f)
                close()
                moveTo(136.048f, 287.962f)
                curveToRelative(-22.224f, 0.0f, -39.909f, -17.686f, -39.909f, -39.902f)
                curveToRelative(0.0f, -22.224f, 17.686f, -39.91f, 39.909f, -39.91f)
                curveToRelative(22.217f, 0.0f, 39.91f, 17.686f, 39.91f, 39.91f)
                curveTo(175.958f, 270.276f, 158.265f, 287.962f, 136.048f, 287.962f)
                lineTo(136.048f, 287.962f)
                close()
                moveTo(239.896f, 272.088f)
                curveToRelative(-26.302f, 0.0f, -48.073f, -21.763f, -48.073f, -48.064f)
                curveToRelative(0.0f, -26.755f, 21.771f, -48.065f, 48.073f, -48.065f)
                curveToRelative(26.755f, 0.0f, 48.065f, 21.311f, 48.065f, 48.065f)
                curveTo(287.962f, 250.325f, 266.651f, 272.088f, 239.896f, 272.088f)
                lineTo(239.896f, 272.088f)
                close()
                moveTo(360.079f, 256.23f)
                curveToRelative(-30.841f, 0.0f, -56.244f, -25.403f, -56.244f, -56.244f)
                curveToRelative(0.0f, -30.833f, 25.403f, -56.229f, 56.244f, -56.229f)
                curveToRelative(30.824f, 0.0f, 56.229f, 25.396f, 56.229f, 56.229f)
                curveTo(416.308f, 230.827f, 390.903f, 256.23f, 360.079f, 256.23f)
                lineTo(360.079f, 256.23f)
                close()
                moveTo(175.958f, 351.908f)
                horizontalLineTo(96.139f)
                verticalLineToRelative(-7.702f)
                curveToRelative(0.0f, -22.232f, 17.686f, -40.371f, 39.909f, -40.371f)
                curveToRelative(21.764f, 0.0f, 39.91f, 18.139f, 39.91f, 40.371f)
                verticalLineTo(351.908f)
                close()
                moveTo(239.896f, 287.962f)
                curveToRelative(26.302f, 0.0f, 48.065f, 21.778f, 48.065f, 48.072f)
                verticalLineToRelative(15.874f)
                horizontalLineToRelative(-96.139f)
                verticalLineToRelative(-15.874f)
                curveTo(191.823f, 309.74f, 213.595f, 287.962f, 239.896f, 287.962f)
                lineTo(239.896f, 287.962f)
                close()
            }
        }
        .build()
        return _myspace!!
    }

private var _myspace: ImageVector? = null
