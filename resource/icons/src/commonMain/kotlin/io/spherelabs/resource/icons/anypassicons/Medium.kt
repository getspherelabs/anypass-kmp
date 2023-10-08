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

public val AnyPassIcons.Medium: ImageVector
  get() {
    if (_medium != null) {
      return _medium!!
    }
    _medium =
      Builder(
          name = "Medium",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFF000000)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
          ) {
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
            pathFillType = NonZero
          ) {
            moveTo(12.8639f, 12.1201f)
            curveTo(12.8639f, 14.1731f, 11.1702f, 15.8401f, 9.0721f, 15.8401f)
            curveTo(6.9739f, 15.8401f, 5.2802f, 14.1731f, 5.2802f, 12.1201f)
            curveTo(5.2802f, 10.0671f, 6.9739f, 8.4001f, 9.0721f, 8.4001f)
            curveTo(11.1702f, 8.4001f, 12.8639f, 10.0671f, 12.8639f, 12.1201f)
            close()
            moveTo(17.0181f, 12.1201f)
            curveTo(17.0181f, 14.0499f, 16.1671f, 15.6184f, 15.1222f, 15.6184f)
            curveTo(14.0773f, 15.6184f, 13.2263f, 14.0499f, 13.2263f, 12.1201f)
            curveTo(13.2263f, 10.1903f, 14.0773f, 8.6218f, 15.1222f, 8.6218f)
            curveTo(16.1671f, 8.6218f, 17.0181f, 10.1821f, 17.0181f, 12.1201f)
            close()
            moveTo(18.7202f, 12.1201f)
            curveTo(18.7202f, 13.8528f, 18.4253f, 15.257f, 18.0546f, 15.257f)
            curveTo(17.6838f, 15.257f, 17.3889f, 13.8528f, 17.3889f, 12.1201f)
            curveTo(17.3889f, 10.3874f, 17.6838f, 8.9831f, 18.0546f, 8.9831f)
            curveTo(18.4253f, 8.9831f, 18.7202f, 10.3874f, 18.7202f, 12.1201f)
            close()
          }
        }
        .build()
    return _medium!!
  }

private var _medium: ImageVector? = null
