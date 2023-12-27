package io.spherelabs.resource.icons.anypassicons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
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

public val AnyPassIcons.ApplePodcasts: ImageVector
  get() {
    if (_applePodcasts != null) {
      return _applePodcasts!!
    }
    _applePodcasts =
        Builder(
                name = "ApplePodcasts",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f)
            .apply {
              path(
                  fill =
                      linearGradient(
                          0.0f to Color(0xFFF452FF),
                          1.0f to Color(0xFF832BC1),
                          start = Offset(12.0f, 0.0f),
                          end = Offset(12.0f, 24.0f)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
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
                  pathFillType = NonZero) {
                    moveTo(13.4231f, 13.9175f)
                    curveTo(13.3998f, 13.7073f, 13.3297f, 13.5554f, 13.1895f, 13.4153f)
                    curveTo(12.9267f, 13.1408f, 12.4654f, 12.9597f, 11.9222f, 12.9597f)
                    curveTo(11.3791f, 12.9597f, 10.9178f, 13.1349f, 10.655f, 13.4153f)
                    curveTo(10.5206f, 13.5613f, 10.4447f, 13.7073f, 10.4214f, 13.9175f)
                    curveTo(10.3746f, 14.3263f, 10.4038f, 14.6767f, 10.4506f, 15.2432f)
                    curveTo(10.4973f, 15.7805f, 10.5849f, 16.4988f, 10.6958f, 17.2229f)
                    curveTo(10.7776f, 17.7427f, 10.8418f, 18.023f, 10.9002f, 18.2216f)
                    curveTo(10.9995f, 18.5486f, 11.3558f, 18.8289f, 11.9222f, 18.8289f)
                    curveTo(12.4887f, 18.8289f, 12.8508f, 18.5428f, 12.9442f, 18.2216f)
                    curveTo(13.0026f, 18.023f, 13.0669f, 17.7427f, 13.1486f, 17.2229f)
                    curveTo(13.2596f, 16.4929f, 13.3472f, 15.7805f, 13.3939f, 15.2432f)
                    curveTo(13.4465f, 14.6767f, 13.4698f, 14.3263f, 13.4231f, 13.9175f)
                    close()
                  }
              path(
                  fill = SolidColor(Color(0xFFffffff)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
                    moveTo(13.358f, 10.9389f)
                    curveTo(13.358f, 11.7332f, 12.7156f, 12.3756f, 11.9214f, 12.3756f)
                    curveTo(11.1271f, 12.3756f, 10.4847f, 11.7332f, 10.4847f, 10.9389f)
                    curveTo(10.4847f, 10.1447f, 11.1271f, 9.5023f, 11.9214f, 9.5023f)
                    curveTo(12.7156f, 9.5023f, 13.358f, 10.1505f, 13.358f, 10.9389f)
                    close()
                  }
              path(
                  fill = SolidColor(Color(0xFFffffff)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
                    moveTo(11.9047f, 5.28f)
                    curveTo(8.5525f, 5.2917f, 5.8077f, 8.0131f, 5.761f, 11.3653f)
                    curveTo(5.7259f, 14.0809f, 7.4604f, 16.4052f, 9.884f, 17.2578f)
                    curveTo(9.9424f, 17.2812f, 10.0008f, 17.2286f, 9.995f, 17.1702f)
                    curveTo(9.9658f, 16.96f, 9.9307f, 16.7498f, 9.9074f, 16.5395f)
                    curveTo(9.8957f, 16.4636f, 9.849f, 16.4052f, 9.7847f, 16.3702f)
                    curveTo(7.8692f, 15.535f, 6.5318f, 13.6137f, 6.5552f, 11.3886f)
                    curveTo(6.5844f, 8.4686f, 8.9671f, 6.0918f, 11.8813f, 6.0684f)
                    curveTo(14.8655f, 6.045f, 17.3008f, 8.4628f, 17.3008f, 11.4412f)
                    curveTo(17.3008f, 13.6429f, 15.9693f, 15.535f, 14.0713f, 16.3702f)
                    curveTo(14.0012f, 16.3994f, 13.9545f, 16.4636f, 13.9487f, 16.5395f)
                    curveTo(13.9195f, 16.7498f, 13.8903f, 16.96f, 13.8611f, 17.1702f)
                    curveTo(13.8494f, 17.2345f, 13.9136f, 17.2812f, 13.972f, 17.2578f)
                    curveTo(16.3723f, 16.4169f, 18.0951f, 14.1276f, 18.0951f, 11.4412f)
                    curveTo(18.0834f, 8.0423f, 15.3094f, 5.2741f, 11.9047f, 5.28f)
                    close()
                  }
              path(
                  fill = SolidColor(Color(0xFFffffff)),
                  stroke = null,
                  strokeLineWidth = 0.0f,
                  strokeLineCap = Butt,
                  strokeLineJoin = Miter,
                  strokeLineMiter = 4.0f,
                  pathFillType = NonZero) {
                    moveTo(11.7637f, 7.3239f)
                    curveTo(9.5795f, 7.4057f, 7.8275f, 9.2103f, 7.7983f, 11.3944f)
                    curveTo(7.7808f, 12.8311f, 8.4991f, 14.1042f, 9.6029f, 14.8575f)
                    curveTo(9.6554f, 14.8926f, 9.7314f, 14.8517f, 9.7314f, 14.7875f)
                    curveTo(9.7138f, 14.5363f, 9.7138f, 14.3144f, 9.7255f, 14.0808f)
                    curveTo(9.7314f, 14.0049f, 9.7022f, 13.9348f, 9.6438f, 13.8823f)
                    curveTo(8.9722f, 13.2515f, 8.5634f, 12.3522f, 8.5867f, 11.3594f)
                    curveTo(8.6334f, 9.6074f, 10.0409f, 8.1824f, 11.7929f, 8.1123f)
                    curveTo(13.6967f, 8.0364f, 15.2618f, 9.5665f, 15.2618f, 11.447f)
                    curveTo(15.2618f, 12.4047f, 14.853f, 13.2691f, 14.2048f, 13.8823f)
                    curveTo(14.1522f, 13.9348f, 14.123f, 14.0049f, 14.123f, 14.0808f)
                    curveTo(14.1347f, 14.3086f, 14.1289f, 14.5305f, 14.1172f, 14.7816f)
                    curveTo(14.1114f, 14.8459f, 14.1873f, 14.8926f, 14.2457f, 14.8517f)
                    curveTo(15.3319f, 14.11f, 16.0502f, 12.8544f, 16.0502f, 11.4411f)
                    curveTo(16.0561f, 9.1168f, 14.1114f, 7.2305f, 11.7637f, 7.3239f)
                    close()
                  }
            }
            .build()
    return _applePodcasts!!
  }

private var _applePodcasts: ImageVector? = null
