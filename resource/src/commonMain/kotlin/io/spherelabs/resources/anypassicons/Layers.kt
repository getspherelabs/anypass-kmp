package io.spherelabs.resources.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resources.AnyPassIcons

public val AnyPassIcons.Layers: ImageVector
  get() {
    if (_layers != null) {
      return _layers!!
    }
    _layers =
      Builder(
          name = "Layers",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill = SolidColor(Color(0xFF28303F)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = EvenOdd
          ) {
            moveTo(10.7702f, 2.2764f)
            curveTo(11.5444f, 1.8893f, 12.4557f, 1.8893f, 13.2299f, 2.2764f)
            lineTo(20.5466f, 5.9348f)
            curveTo(21.8364f, 6.5797f, 21.8364f, 8.4204f, 20.5466f, 9.0653f)
            lineTo(13.2299f, 12.7236f)
            curveTo(12.4557f, 13.1107f, 11.5444f, 13.1107f, 10.7702f, 12.7236f)
            lineTo(3.4535f, 9.0653f)
            curveTo(2.1636f, 8.4203f, 2.1636f, 6.5797f, 3.4535f, 5.9348f)
            lineTo(10.7702f, 2.2764f)
            close()
            moveTo(2.3146f, 17.1953f)
            curveTo(2.4828f, 16.8168f, 2.926f, 16.6464f, 3.3045f, 16.8146f)
            lineTo(11.4923f, 20.4536f)
            curveTo(11.8155f, 20.5972f, 12.1844f, 20.5972f, 12.5076f, 20.4536f)
            lineTo(20.6953f, 16.8146f)
            curveTo(21.0739f, 16.6464f, 21.5171f, 16.8168f, 21.6853f, 17.1953f)
            curveTo(21.8535f, 17.5739f, 21.6831f, 18.0171f, 21.3045f, 18.1853f)
            lineTo(13.1168f, 21.8243f)
            curveTo(12.4058f, 22.1403f, 11.5941f, 22.1403f, 10.8831f, 21.8243f)
            lineTo(2.6953f, 18.1853f)
            curveTo(2.3168f, 18.0171f, 2.1464f, 17.5739f, 2.3146f, 17.1953f)
            close()
            moveTo(3.3045f, 12.3146f)
            curveTo(2.926f, 12.1464f, 2.4828f, 12.3168f, 2.3146f, 12.6953f)
            curveTo(2.1464f, 13.0739f, 2.3168f, 13.5171f, 2.6953f, 13.6853f)
            lineTo(10.8831f, 17.3243f)
            curveTo(11.5941f, 17.6403f, 12.4058f, 17.6403f, 13.1168f, 17.3243f)
            lineTo(21.3045f, 13.6853f)
            curveTo(21.6831f, 13.5171f, 21.8535f, 13.0739f, 21.6853f, 12.6953f)
            curveTo(21.5171f, 12.3168f, 21.0739f, 12.1464f, 20.6953f, 12.3146f)
            lineTo(12.5076f, 15.9536f)
            curveTo(12.1844f, 16.0972f, 11.8155f, 16.0972f, 11.4923f, 15.9536f)
            lineTo(3.3045f, 12.3146f)
            close()
          }
        }
        .build()
    return _layers!!
  }

private var _layers: ImageVector? = null
