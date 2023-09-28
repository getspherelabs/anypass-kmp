package io.spherelabs.resources.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resources.AnyPassIcons

public val AnyPassIcons.Skype: ImageVector
    get() {
        if (_skype != null) {
            return _skype!!
        }
        _skype = Builder(name = "Skype", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF00AEF3)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
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
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(18.2995f, 14.8145f)
                curveTo(18.2995f, 14.2582f, 18.1673f, 13.733f, 17.9321f, 13.2664f)
                curveTo(18.0245f, 12.8501f, 18.0726f, 12.419f, 18.0726f, 11.9761f)
                curveTo(18.0726f, 8.6577f, 15.3443f, 5.9667f, 11.9774f, 5.9667f)
                curveTo(11.6227f, 5.9667f, 11.2748f, 5.997f, 10.9352f, 6.0548f)
                curveTo(10.3905f, 5.7163f, 9.7458f, 5.52f, 9.0553f, 5.52f)
                curveTo(7.1024f, 5.52f, 5.52f, 7.0799f, 5.52f, 9.0058f)
                curveTo(5.52f, 9.6487f, 5.6973f, 10.2502f, 6.0054f, 10.7687f)
                curveTo(5.925f, 11.1583f, 5.8822f, 11.5627f, 5.8822f, 11.9761f)
                curveTo(5.8822f, 15.2959f, 8.6112f, 17.9862f, 11.9774f, 17.9862f)
                curveTo(12.3591f, 17.9862f, 12.7318f, 17.9514f, 13.0939f, 17.8855f)
                curveTo(13.5921f, 18.1492f, 14.1601f, 18.2995f, 14.765f, 18.2995f)
                curveTo(16.7171f, 18.2995f, 18.2995f, 16.7388f, 18.2995f, 14.8145f)
                close()
                moveTo(15.5307f, 13.5079f)
                curveTo(15.5307f, 14.0026f, 15.3864f, 14.4537f, 15.1039f, 14.8485f)
                curveTo(14.8221f, 15.2426f, 14.4059f, 15.5537f, 13.8649f, 15.7744f)
                curveTo(13.3306f, 15.9937f, 12.6904f, 16.1055f, 11.9609f, 16.1055f)
                curveTo(11.0855f, 16.1055f, 10.3514f, 15.9537f, 9.7781f, 15.6537f)
                curveTo(9.3686f, 15.4359f, 9.0312f, 15.1411f, 8.775f, 14.7752f)
                curveTo(8.5158f, 14.4078f, 8.385f, 14.0426f, 8.385f, 13.6893f)
                curveTo(8.385f, 13.4701f, 8.4707f, 13.279f, 8.639f, 13.1227f)
                curveTo(8.8058f, 12.9671f, 9.0199f, 12.8894f, 9.2754f, 12.8894f)
                curveTo(9.4843f, 12.8894f, 9.6654f, 12.9508f, 9.8119f, 13.0731f)
                curveTo(9.9524f, 13.1908f, 10.0726f, 13.3649f, 10.1696f, 13.5878f)
                curveTo(10.2778f, 13.8323f, 10.395f, 14.0382f, 10.5189f, 14.1989f)
                curveTo(10.6392f, 14.356f, 10.8112f, 14.4871f, 11.0306f, 14.59f)
                curveTo(11.2515f, 14.6923f, 11.5498f, 14.7456f, 11.9158f, 14.7456f)
                curveTo(12.4199f, 14.7456f, 12.8332f, 14.6389f, 13.1435f, 14.43f)
                curveTo(13.4478f, 14.2263f, 13.5951f, 13.9804f, 13.5951f, 13.6797f)
                curveTo(13.5951f, 13.4434f, 13.5177f, 13.2568f, 13.3599f, 13.1101f)
                curveTo(13.1931f, 12.9575f, 12.9745f, 12.839f, 12.7092f, 12.756f)
                curveTo(12.432f, 12.6716f, 12.0563f, 12.579f, 11.5912f, 12.4827f)
                curveTo(10.9578f, 12.3494f, 10.4205f, 12.1916f, 9.9937f, 12.0116f)
                curveTo(9.5564f, 11.8287f, 9.204f, 11.5746f, 8.9463f, 11.2568f)
                curveTo(8.6848f, 10.9331f, 8.5518f, 10.528f, 8.5518f, 10.0517f)
                curveTo(8.5518f, 9.5984f, 8.6908f, 9.1888f, 8.9658f, 8.8362f)
                curveTo(9.2378f, 8.4858f, 9.6361f, 8.2118f, 10.1493f, 8.0258f)
                curveTo(10.6549f, 7.8414f, 11.2561f, 7.7481f, 11.9376f, 7.7481f)
                curveTo(12.4816f, 7.7481f, 12.9602f, 7.8103f, 13.3599f, 7.9332f)
                curveTo(13.7619f, 8.0562f, 14.1f, 8.2221f, 14.366f, 8.4273f)
                curveTo(14.6343f, 8.6347f, 14.8341f, 8.8547f, 14.9589f, 9.0843f)
                curveTo(15.0851f, 9.3162f, 15.1497f, 9.5465f, 15.1497f, 9.7695f)
                curveTo(15.1497f, 9.9843f, 15.0656f, 10.1798f, 14.8995f, 10.3495f)
                curveTo(14.7327f, 10.5213f, 14.5216f, 10.608f, 14.2736f, 10.608f)
                curveTo(14.0482f, 10.608f, 13.8709f, 10.5531f, 13.7491f, 10.4465f)
                curveTo(13.6349f, 10.3465f, 13.5162f, 10.1909f, 13.3847f, 9.9687f)
                curveTo(13.2329f, 9.6843f, 13.0489f, 9.4599f, 12.8377f, 9.3013f)
                curveTo(12.6326f, 9.1473f, 12.2907f, 9.0702f, 11.8196f, 9.0702f)
                curveTo(11.3838f, 9.0702f, 11.0276f, 9.1562f, 10.7639f, 9.3273f)
                curveTo(10.5099f, 9.491f, 10.386f, 9.6798f, 10.386f, 9.9035f)
                curveTo(10.386f, 10.0398f, 10.4258f, 10.1546f, 10.5084f, 10.2524f)
                curveTo(10.5941f, 10.3576f, 10.7166f, 10.448f, 10.8713f, 10.525f)
                curveTo(11.0314f, 10.6043f, 11.1967f, 10.668f, 11.362f, 10.7124f)
                curveTo(11.5311f, 10.7591f, 11.8151f, 10.828f, 12.2051f, 10.9168f)
                curveTo(12.6987f, 11.0205f, 13.1518f, 11.1383f, 13.5523f, 11.265f)
                curveTo(13.9588f, 11.3924f, 14.3089f, 11.5494f, 14.596f, 11.7338f)
                curveTo(14.8875f, 11.9212f, 15.1182f, 12.1612f, 15.2827f, 12.4486f)
                curveTo(15.4473f, 12.7383f, 15.5307f, 13.0938f, 15.5307f, 13.5079f)
                close()
            }
        }
        .build()
        return _skype!!
    }

private var _skype: ImageVector? = null
