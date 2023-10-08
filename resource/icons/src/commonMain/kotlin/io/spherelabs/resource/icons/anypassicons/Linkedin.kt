package io.spherelabs.resource.icons.anypassicons

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
import io.spherelabs.resource.icons.AnyPassIcons

public val AnyPassIcons.Linkedin: ImageVector
  get() {
    if (_linkedin != null) {
      return _linkedin!!
    }
    _linkedin =
      Builder(
          name = "Linkedin",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFF006699)),
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
            moveTo(6.4889f, 5.52f)
            curveTo(5.954f, 5.52f, 5.52f, 5.9407f, 5.52f, 6.4591f)
            verticalLineTo(17.6912f)
            curveTo(5.52f, 18.2096f, 5.954f, 18.63f, 6.4889f, 18.63f)
            horizontalLineTo(17.6611f)
            curveTo(18.1963f, 18.63f, 18.63f, 18.2095f, 18.63f, 17.691f)
            verticalLineTo(6.4591f)
            curveTo(18.63f, 5.9407f, 18.1963f, 5.52f, 17.6611f, 5.52f)
            horizontalLineTo(6.4889f)
            close()
            moveTo(9.5039f, 10.5892f)
            verticalLineTo(16.4914f)
            horizontalLineTo(7.5421f)
            verticalLineTo(10.5892f)
            horizontalLineTo(9.5039f)
            close()
            moveTo(9.6332f, 8.7639f)
            curveTo(9.6332f, 9.3303f, 9.2074f, 9.7835f, 8.5234f, 9.7835f)
            lineTo(8.5106f, 9.7835f)
            curveTo(7.8522f, 9.7835f, 7.4265f, 9.3302f, 7.4265f, 8.7638f)
            curveTo(7.4265f, 8.1846f, 7.865f, 7.744f, 8.5364f, 7.744f)
            curveTo(9.2074f, 7.744f, 9.6204f, 8.1846f, 9.6332f, 8.7639f)
            close()
            moveTo(12.5514f, 16.4914f)
            horizontalLineTo(10.5898f)
            curveTo(10.5898f, 16.4914f, 10.6155f, 11.1431f, 10.5899f, 10.5894f)
            horizontalLineTo(12.5516f)
            verticalLineTo(11.4248f)
            curveTo(12.8124f, 11.0227f, 13.2791f, 10.4508f, 14.3196f, 10.4508f)
            curveTo(15.6103f, 10.4508f, 16.578f, 11.2944f, 16.578f, 13.1072f)
            verticalLineTo(16.4914f)
            horizontalLineTo(14.6164f)
            verticalLineTo(13.3342f)
            curveTo(14.6164f, 12.5407f, 14.3323f, 11.9995f, 13.6227f, 11.9995f)
            curveTo(13.0806f, 11.9995f, 12.7578f, 12.3645f, 12.6161f, 12.7171f)
            curveTo(12.5642f, 12.8429f, 12.5514f, 13.0195f, 12.5514f, 13.1957f)
            verticalLineTo(16.4914f)
            close()
          }
        }
        .build()
    return _linkedin!!
  }

private var _linkedin: ImageVector? = null
