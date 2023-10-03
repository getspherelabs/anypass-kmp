package io.spherelabs.resources.anypassicons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
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

public val AnyPassIcons.Messenger: ImageVector
  get() {
    if (_messenger != null) {
      return _messenger!!
    }
    _messenger =
      Builder(
          name = "Messenger",
          defaultWidth = 24.0.dp,
          defaultHeight = 24.0.dp,
          viewportWidth = 24.0f,
          viewportHeight = 24.0f
        )
        .apply {
          path(
            fill =
              radialGradient(
                0.0f to Color(0xFF0099FF),
                0.6f to Color(0xFFA033FF),
                0.9f to Color(0xFFFF5280),
                1.0f to Color(0xFFFF7061),
                center = Offset(4.02f, 23.9993f),
                radius = 26.4f
              ),
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
            moveTo(6.0f, 11.8247f)
            curveTo(6.0f, 8.4785f, 8.6227f, 6.0f, 12.005f, 6.0f)
            curveTo(15.3873f, 6.0f, 18.01f, 8.48f, 18.01f, 11.8262f)
            curveTo(18.01f, 15.1724f, 15.3873f, 17.6509f, 12.005f, 17.6509f)
            curveTo(11.397f, 17.6509f, 10.8145f, 17.5698f, 10.2666f, 17.4197f)
            curveTo(10.16f, 17.3912f, 10.0459f, 17.3987f, 9.9453f, 17.4437f)
            lineTo(8.7533f, 17.9691f)
            curveTo(8.6813f, 18.0009f, 8.6026f, 18.0145f, 8.5241f, 18.0087f)
            curveTo(8.4457f, 18.0029f, 8.3698f, 17.978f, 8.3033f, 17.936f)
            curveTo(8.2367f, 17.8941f, 8.1814f, 17.8364f, 8.1424f, 17.7681f)
            curveTo(8.1033f, 17.6998f, 8.0816f, 17.6229f, 8.0792f, 17.5443f)
            lineTo(8.0462f, 16.4754f)
            curveTo(8.0432f, 16.3433f, 7.9832f, 16.2202f, 7.8856f, 16.1332f)
            curveTo(6.7176f, 15.0883f, 6.0f, 13.5751f, 6.0f, 11.8247f)
            close()
            moveTo(10.163f, 10.7303f)
            lineTo(8.399f, 13.5286f)
            curveTo(8.2294f, 13.7973f, 8.5596f, 14.099f, 8.8118f, 13.9069f)
            lineTo(10.7064f, 12.4687f)
            curveTo(10.7688f, 12.4214f, 10.8448f, 12.3956f, 10.9231f, 12.3953f)
            curveTo(11.0014f, 12.3951f, 11.0776f, 12.4203f, 11.1403f, 12.4672f)
            lineTo(12.5439f, 13.5196f)
            curveTo(12.6435f, 13.5943f, 12.7575f, 13.6476f, 12.8787f, 13.6761f)
            curveTo(12.9999f, 13.7046f, 13.1257f, 13.7077f, 13.2481f, 13.6851f)
            curveTo(13.3706f, 13.6626f, 13.487f, 13.6148f, 13.5901f, 13.545f)
            curveTo(13.6932f, 13.4752f, 13.7807f, 13.3847f, 13.847f, 13.2794f)
            lineTo(15.6125f, 10.4826f)
            curveTo(15.7806f, 10.2139f, 15.4504f, 9.9107f, 15.1982f, 10.1028f)
            lineTo(13.3036f, 11.541f)
            curveTo(13.2412f, 11.5883f, 13.1652f, 11.6141f, 13.0869f, 11.6143f)
            curveTo(13.0086f, 11.6146f, 12.9324f, 11.5894f, 12.8697f, 11.5425f)
            lineTo(11.466f, 10.4901f)
            curveTo(11.3665f, 10.4153f, 11.2525f, 10.362f, 11.1313f, 10.3335f)
            curveTo(11.0101f, 10.305f, 10.8843f, 10.302f, 10.7618f, 10.3245f)
            curveTo(10.6394f, 10.3471f, 10.523f, 10.3948f, 10.4199f, 10.4647f)
            curveTo(10.3168f, 10.5345f, 10.2293f, 10.625f, 10.163f, 10.7303f)
            close()
          }
        }
        .build()
    return _messenger!!
  }

private var _messenger: ImageVector? = null
