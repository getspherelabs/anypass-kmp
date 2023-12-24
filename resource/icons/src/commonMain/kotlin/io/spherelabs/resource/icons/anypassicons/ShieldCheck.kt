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

public val AnyPassIcons.ShieldCheck: ImageVector
  get() {
    if (_shieldCheck != null) {
      return _shieldCheck!!
    }
    _shieldCheck =
        Builder(
                name = "ShieldCheck",
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
                    moveTo(5.3325f, 4.7107f)
                    lineTo(10.3708f, 2.3642f)
                    curveTo(11.4134f, 1.8786f, 12.6036f, 1.8786f, 13.6462f, 2.3642f)
                    lineTo(18.6646f, 4.7014f)
                    curveTo(20.1321f, 5.3849f, 21.0953f, 6.9209f, 20.9925f, 8.6003f)
                    curveTo(20.5981f, 15.0419f, 18.8406f, 17.9539f, 14.0899f, 21.3322f)
                    curveTo(12.8361f, 22.2238f, 11.1823f, 22.2216f, 9.9276f, 21.3315f)
                    curveTo(5.1914f, 17.9715f, 3.3687f, 15.1001f, 3.0062f, 8.5777f)
                    curveTo(2.9134f, 6.9094f, 3.8751f, 5.3894f, 5.3325f, 4.7107f)
                    close()
                    moveTo(15.5644f, 10.4939f)
                    curveTo(15.8372f, 10.1822f, 15.8056f, 9.7083f, 15.4938f, 9.4356f)
                    curveTo(15.1821f, 9.1628f, 14.7083f, 9.1944f, 14.4355f, 9.5061f)
                    lineTo(11.5656f, 12.786f)
                    curveTo(11.4775f, 12.8867f, 11.3258f, 12.9002f, 11.2213f, 12.8166f)
                    lineTo(9.4685f, 11.4144f)
                    curveTo(9.145f, 11.1556f, 8.6731f, 11.208f, 8.4143f, 11.5315f)
                    curveTo(8.1556f, 11.8549f, 8.208f, 12.3269f, 8.5315f, 12.5857f)
                    lineTo(10.2843f, 13.9879f)
                    curveTo(11.0156f, 14.5729f, 12.0778f, 14.4786f, 12.6945f, 13.7738f)
                    lineTo(15.5644f, 10.4939f)
                    close()
                  }
            }
            .build()
    return _shieldCheck!!
  }

private var _shieldCheck: ImageVector? = null
