package io.spherelabs.resource.icons.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.AnyPassIcons

public val AnyPassIcons.Target: ImageVector
  get() {
    if (_target != null) {
      return _target!!
    }
    _target =
        Builder(
                name = "Target",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f)
            .apply {
              path(
                  fill = SolidColor(Color(0xFF28303F)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = EvenOdd) {
                    moveTo(22.0f, 12.0f)
                    curveTo(22.0f, 17.5228f, 17.5228f, 22.0f, 12.0f, 22.0f)
                    curveTo(6.4771f, 22.0f, 2.0f, 17.5228f, 2.0f, 12.0f)
                    curveTo(2.0f, 6.4771f, 6.4771f, 2.0f, 12.0f, 2.0f)
                    curveTo(17.5228f, 2.0f, 22.0f, 6.4771f, 22.0f, 12.0f)
                    close()
                    moveTo(12.0f, 7.75f)
                    curveTo(9.6528f, 7.75f, 7.75f, 9.6528f, 7.75f, 12.0f)
                    curveTo(7.75f, 14.3472f, 9.6528f, 16.25f, 12.0f, 16.25f)
                    curveTo(14.3472f, 16.25f, 16.25f, 14.3472f, 16.25f, 12.0f)
                    curveTo(16.25f, 9.6528f, 14.3472f, 7.75f, 12.0f, 7.75f)
                    close()
                    moveTo(6.25f, 12.0f)
                    curveTo(6.25f, 8.8244f, 8.8244f, 6.25f, 12.0f, 6.25f)
                    curveTo(15.1756f, 6.25f, 17.75f, 8.8244f, 17.75f, 12.0f)
                    curveTo(17.75f, 15.1756f, 15.1756f, 17.75f, 12.0f, 17.75f)
                    curveTo(8.8244f, 17.75f, 6.25f, 15.1756f, 6.25f, 12.0f)
                    close()
                    moveTo(12.0f, 13.0f)
                    curveTo(12.5523f, 13.0f, 13.0f, 12.5523f, 13.0f, 12.0f)
                    curveTo(13.0f, 11.4477f, 12.5523f, 11.0f, 12.0f, 11.0f)
                    curveTo(11.4477f, 11.0f, 11.0f, 11.4477f, 11.0f, 12.0f)
                    curveTo(11.0f, 12.5523f, 11.4477f, 13.0f, 12.0f, 13.0f)
                    close()
                  }
            }
            .build()
    return _target!!
  }

private var _target: ImageVector? = null
