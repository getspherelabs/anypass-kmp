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

public val AnyPassIcons.Quora: ImageVector
  get() {
    if (_quora != null) {
      return _quora!!
    }
    _quora =
      Builder(
          name = "Quora",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFFB92B27)),
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
            moveTo(12.4462f, 15.3627f)
            curveTo(12.0282f, 14.5439f, 11.5378f, 13.7168f, 10.5811f, 13.7168f)
            curveTo(10.3983f, 13.7168f, 10.2156f, 13.7469f, 10.048f, 13.8231f)
            lineTo(9.723f, 13.1758f)
            curveTo(10.1191f, 12.8377f, 10.759f, 12.5696f, 11.5816f, 12.5696f)
            curveTo(12.8612f, 12.5696f, 13.5179f, 13.183f, 14.0393f, 13.966f)
            curveTo(14.3488f, 13.2975f, 14.4959f, 12.3947f, 14.4959f, 11.2757f)
            curveTo(14.4959f, 8.4813f, 13.6177f, 7.0464f, 11.5665f, 7.0464f)
            curveTo(9.5452f, 7.0464f, 8.6718f, 8.4813f, 8.6718f, 11.2757f)
            curveTo(8.6718f, 14.0554f, 9.5452f, 15.4755f, 11.5665f, 15.4755f)
            curveTo(11.8878f, 15.4755f, 12.1788f, 15.4403f, 12.4462f, 15.3627f)
            close()
            moveTo(12.9473f, 16.3379f)
            curveTo(12.497f, 16.4584f, 12.0328f, 16.5201f, 11.5665f, 16.5214f)
            curveTo(8.875f, 16.5214f, 6.2396f, 14.384f, 6.2396f, 11.2757f)
            curveTo(6.2396f, 8.1379f, 8.875f, 6.0001f, 11.5665f, 6.0001f)
            curveTo(14.3032f, 6.0001f, 16.9133f, 8.1225f, 16.9133f, 11.2757f)
            curveTo(16.9133f, 13.0296f, 16.0909f, 14.4549f, 14.8956f, 15.3761f)
            curveTo(15.2818f, 15.952f, 15.6794f, 16.3344f, 16.233f, 16.3344f)
            curveTo(16.8372f, 16.3344f, 17.0809f, 15.8698f, 17.1216f, 15.5054f)
            horizontalLineTo(17.9085f)
            curveTo(17.9544f, 15.9906f, 17.7105f, 18.0072f, 15.5116f, 18.0072f)
            curveTo(14.1796f, 18.0072f, 13.4754f, 17.239f, 12.9473f, 16.3379f)
            close()
          }
        }
        .build()
    return _quora!!
  }

private var _quora: ImageVector? = null
