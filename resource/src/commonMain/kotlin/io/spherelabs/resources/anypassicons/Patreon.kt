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

public val AnyPassIcons.Patreon: ImageVector
  get() {
    if (_patreon != null) {
      return _patreon!!
    }
    _patreon =
      Builder(
          name = "Patreon",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFFFF424D)),
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
            moveTo(13.7721f, 6.0001f)
            curveTo(11.2903f, 6.0001f, 9.2714f, 8.021f, 9.2714f, 10.5051f)
            curveTo(9.2714f, 12.9817f, 11.2905f, 14.9966f, 13.7721f, 14.9966f)
            curveTo(16.2461f, 14.9966f, 18.2588f, 12.9817f, 18.2588f, 10.5051f)
            curveTo(18.259f, 8.021f, 16.2461f, 6.0001f, 13.7721f, 6.0001f)
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
            moveTo(5.7594f, 18.0f)
            horizontalLineTo(7.9569f)
            verticalLineTo(6.0001f)
            horizontalLineTo(5.7594f)
            verticalLineTo(18.0f)
            close()
          }
        }
        .build()
    return _patreon!!
  }

private var _patreon: ImageVector? = null
