package io.spherelabs.resource.icons.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.AnyPassIcons

public val AnyPassIcons.Facebook: ImageVector
  get() {
    if (_facebook != null) {
      return _facebook!!
    }
    _facebook =
        Builder(
                name = "Facebook",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f)
            .apply {
              path(
                  fill = SolidColor(Color(0xFF337FFF)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
                    moveTo(6.0f, 0.0f)
                    lineTo(18.0f, 0.0f)
                    arcTo(6.0f, 6.0f, 0.0f, false, true, 24.0f, 6.0f)
                    lineTo(24.0f, 18.0f)
                    arcTo(6.0f, 6.0f, 0.0f, false, true, 18.0f, 24.0f)
                    lineTo(6.0f, 24.0f)
                    arcTo(6.0f, 6.0f, 0.0f, false, true, 0.0f, 18.0f)
                    lineTo(0.0f, 6.0f)
                    arcTo(6.0f, 6.0f, 0.0f, false, true, 6.0f, 0.0f)
                    close()
                  }
              path(
                  fill = SolidColor(Color(0xFFffffff)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
                    moveTo(14.8077f, 12.7495f)
                    lineTo(15.1497f, 10.5788f)
                    horizontalLineTo(13.0433f)
                    verticalLineTo(9.1678f)
                    curveTo(13.0433f, 8.5743f, 13.3374f, 7.9943f, 14.2777f, 7.9943f)
                    horizontalLineTo(15.2489f)
                    verticalLineTo(6.1458f)
                    curveTo(14.6833f, 6.0557f, 14.1119f, 6.0069f, 13.5391f, 5.9999f)
                    curveTo(11.8054f, 5.9999f, 10.6735f, 7.0412f, 10.6735f, 8.9236f)
                    verticalLineTo(10.5788f)
                    horizontalLineTo(8.7518f)
                    verticalLineTo(12.7495f)
                    horizontalLineTo(10.6735f)
                    verticalLineTo(17.9999f)
                    horizontalLineTo(13.0433f)
                    verticalLineTo(12.7495f)
                    horizontalLineTo(14.8077f)
                    close()
                  }
            }
            .build()
    return _facebook!!
  }

private var _facebook: ImageVector? = null
