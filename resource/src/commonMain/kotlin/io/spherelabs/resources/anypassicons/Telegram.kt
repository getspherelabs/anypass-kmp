package io.spherelabs.resources.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resources.AnyPassIcons

public val AnyPassIcons.Telegram: ImageVector
  get() {
    if (_telegram != null) {
      return _telegram!!
    }
    _telegram =
      Builder(
          name = "Telegram",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFF34AADF)),
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
            pathFillType = EvenOdd
          ) {
            moveTo(18.63f, 12.075f)
            curveTo(18.63f, 15.6952f, 15.6952f, 18.63f, 12.075f, 18.63f)
            curveTo(8.4548f, 18.63f, 5.52f, 15.6952f, 5.52f, 12.075f)
            curveTo(5.52f, 8.4548f, 8.4548f, 5.52f, 12.075f, 5.52f)
            curveTo(15.6952f, 5.52f, 18.63f, 8.4548f, 18.63f, 12.075f)
            close()
            moveTo(12.7032f, 10.0636f)
            curveTo(11.5771f, 10.55f, 8.33f, 11.9314f, 8.33f, 11.9314f)
            curveTo(7.5605f, 12.2427f, 8.011f, 12.5346f, 8.011f, 12.5346f)
            curveTo(8.011f, 12.5346f, 8.6679f, 12.7681f, 9.2309f, 12.9432f)
            curveTo(9.794f, 13.1183f, 10.0943f, 12.9237f, 10.0943f, 12.9237f)
            curveTo(10.0943f, 12.9237f, 11.4081f, 12.0287f, 12.7407f, 11.0753f)
            curveTo(13.6791f, 10.4138f, 13.4539f, 10.9586f, 13.2287f, 11.1921f)
            curveTo(12.7407f, 11.698f, 11.9337f, 12.4957f, 11.258f, 13.1377f)
            curveTo(10.9577f, 13.4101f, 11.1078f, 13.6436f, 11.2392f, 13.7603f)
            curveTo(11.6213f, 14.0955f, 12.5213f, 14.7051f, 12.9299f, 14.9818f)
            curveTo(13.0431f, 15.0585f, 13.1186f, 15.1096f, 13.1349f, 15.1223f)
            curveTo(13.2287f, 15.2001f, 13.7542f, 15.5503f, 14.0733f, 15.4725f)
            curveTo(14.3924f, 15.3947f, 14.4299f, 14.9472f, 14.4299f, 14.9472f)
            curveTo(14.4299f, 14.9472f, 14.6739f, 13.3518f, 14.8991f, 11.8925f)
            curveTo(14.9409f, 11.606f, 14.9826f, 11.3255f, 15.0215f, 11.064f)
            curveTo(15.1226f, 10.3841f, 15.2046f, 9.8323f, 15.2182f, 9.6356f)
            curveTo(15.2745f, 8.9741f, 14.5988f, 9.2465f, 14.5988f, 9.2465f)
            curveTo(14.5988f, 9.2465f, 13.1349f, 9.8691f, 12.7032f, 10.0636f)
            close()
          }
        }
        .build()
    return _telegram!!
  }

private var _telegram: ImageVector? = null
