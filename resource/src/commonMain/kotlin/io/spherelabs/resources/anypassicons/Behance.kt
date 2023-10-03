package io.spherelabs.resources.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resources.AnyPassIcons

public val AnyPassIcons.Behance: ImageVector
  get() {
    if (_behance != null) {
      return _behance!!
    }
    _behance =
      Builder(
          name = "Behance",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFF005CFF)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
          ) {
            moveTo(12.0f, 0.0f)
            lineTo(12.0f, 0.0f)
            arcTo(12.0f, 12.0f, 0.0f, false, true, 24.0f, 12.0f)
            lineTo(24.0f, 12.0f)
            arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 24.0f)
            lineTo(12.0f, 24.0f)
            arcTo(12.0f, 12.0f, 0.0f, false, true, 0.0f, 12.0f)
            lineTo(0.0f, 12.0f)
            arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 0.0f)
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
            moveTo(10.8457f, 11.6391f)
            curveTo(10.8457f, 11.6391f, 11.9815f, 11.5528f, 11.9815f, 10.1937f)
            curveTo(11.9815f, 8.8357f, 11.2112f, 8.1664f, 10.0337f, 8.1664f)
            lineTo(6.0001f, 8.16f)
            verticalLineTo(15.6645f)
            horizontalLineTo(10.1275f)
            curveTo(10.1275f, 15.6645f, 12.1913f, 15.7298f, 12.1913f, 13.4131f)
            curveTo(12.1913f, 13.4131f, 12.3428f, 11.6391f, 10.8457f, 11.6391f)
            close()
            moveTo(7.5009f, 9.2856f)
            horizontalLineTo(9.5647f)
            curveTo(9.5647f, 9.2856f, 10.2709f, 9.2973f, 10.2709f, 10.2128f)
            curveTo(10.2709f, 11.1618f, 9.5647f, 11.1618f, 9.5647f, 11.1618f)
            horizontalLineTo(7.5009f)
            verticalLineTo(9.2856f)
            close()
            moveTo(9.7523f, 14.5388f)
            horizontalLineTo(7.5009f)
            verticalLineTo(12.2874f)
            horizontalLineTo(9.7523f)
            curveTo(9.7523f, 12.2874f, 10.878f, 12.2991f, 10.878f, 13.4131f)
            curveTo(10.878f, 14.5272f, 9.9144f, 14.5388f, 9.7523f, 14.5388f)
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
            moveTo(15.3808f, 10.0363f)
            curveTo(12.5617f, 10.0363f, 12.5666f, 12.8505f, 12.5666f, 12.8505f)
            curveTo(12.5666f, 12.8505f, 12.379f, 15.6646f, 15.3808f, 15.6646f)
            curveTo(15.3808f, 15.6646f, 18.0073f, 15.6646f, 18.0073f, 13.4133f)
            horizontalLineTo(16.5064f)
            curveTo(16.5064f, 13.4133f, 16.5064f, 14.539f, 15.3808f, 14.539f)
            curveTo(15.3808f, 14.539f, 14.2551f, 14.539f, 14.2551f, 13.0381f)
            curveTo(14.2551f, 13.0381f, 17.2569f, 13.0381f, 18.0073f, 13.0381f)
            curveTo(18.0073f, 12.2876f, 18.0073f, 10.0363f, 15.3808f, 10.0363f)
            close()
            moveTo(14.2551f, 12.2876f)
            curveTo(14.2551f, 12.2876f, 14.2303f, 11.1619f, 15.3808f, 11.1619f)
            curveTo(16.5308f, 11.1619f, 16.5064f, 12.2876f, 16.5064f, 12.2876f)
            horizontalLineTo(14.2551f)
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
            moveTo(16.8818f, 8.9105f)
            horizontalLineTo(13.88f)
            verticalLineTo(9.6609f)
            horizontalLineTo(16.8818f)
            verticalLineTo(8.9105f)
            close()
          }
        }
        .build()
    return _behance!!
  }

private var _behance: ImageVector? = null
