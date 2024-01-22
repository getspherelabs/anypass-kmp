package io.spherelabs.resource.icons.socialmedia

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.SocialMediaIcons

public val SocialMediaIcons.Steemit: ImageVector
    get() {
        if (_steemit != null) {
            return _steemit!!
        }
        _steemit = Builder(name = "Steemit", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(9.0799f, 2.3564f)
                curveTo(11.0448f, 1.4401f, 13.3169f, 1.468f, 15.3825f, 2.3796f)
                curveTo(17.4876f, 3.3087f, 19.3776f, 5.5103f, 20.3452f, 7.5854f)
                curveTo(20.5786f, 8.0859f, 20.362f, 8.6809f, 19.8615f, 8.9143f)
                lineTo(18.0592f, 9.7548f)
                curveTo(18.6867f, 10.3459f, 19.2148f, 11.0635f, 19.6009f, 11.8914f)
                curveTo(21.2347f, 15.3951f, 19.7188f, 19.56f, 16.215f, 21.1938f)
                lineTo(14.402f, 22.0393f)
                curveTo(12.437f, 22.9556f, 10.165f, 22.9277f, 8.0994f, 22.0161f)
                curveTo(5.9942f, 21.087f, 4.1043f, 18.8854f, 3.1367f, 16.8103f)
                curveTo(3.0246f, 16.5699f, 3.0126f, 16.2949f, 3.1033f, 16.0457f)
                curveTo(3.194f, 15.7965f, 3.38f, 15.5935f, 3.6203f, 15.4814f)
                lineTo(5.4227f, 14.6409f)
                curveTo(4.7951f, 14.0498f, 4.267f, 13.3322f, 3.881f, 12.5043f)
                curveTo(2.2472f, 9.0006f, 3.763f, 4.8357f, 7.2668f, 3.2018f)
                lineTo(7.2668f, 3.2018f)
                lineTo(9.0799f, 2.3564f)
                lineTo(9.5016f, 3.2609f)
                lineTo(9.0799f, 2.3564f)
                close()
                moveTo(12.3862f, 12.4961f)
                curveTo(13.6375f, 11.9126f, 15.125f, 12.4539f, 15.7085f, 13.7053f)
                curveTo(16.292f, 14.9566f, 15.7506f, 16.4441f, 14.4992f, 17.0276f)
                lineTo(12.6866f, 17.8728f)
                curveTo(12.0857f, 18.1531f, 11.3981f, 18.1831f, 10.775f, 17.9563f)
                curveTo(10.4785f, 17.8484f, 10.2082f, 17.6865f, 9.9761f, 17.4817f)
                lineTo(10.6932f, 17.1473f)
                curveTo(11.1938f, 16.9139f, 11.4103f, 16.3189f, 11.1769f, 15.8184f)
                curveTo(10.5934f, 14.567f, 11.1348f, 13.0796f, 12.3862f, 12.4961f)
                close()
                moveTo(11.5409f, 10.6835f)
                curveTo(9.6f, 11.5885f, 8.608f, 13.7018f, 9.0428f, 15.7102f)
                lineTo(8.0354f, 16.1799f)
                curveTo(7.5349f, 16.4133f, 7.3183f, 17.0083f, 7.5517f, 17.5089f)
                curveTo(8.0561f, 18.5905f, 8.9695f, 19.4275f, 10.091f, 19.8357f)
                curveTo(11.2125f, 20.2439f, 12.4502f, 20.1898f, 13.5319f, 19.6855f)
                lineTo(15.3445f, 18.8402f)
                curveTo(17.5969f, 17.7899f, 18.5714f, 15.1125f, 17.5211f, 12.8601f)
                curveTo(16.4708f, 10.6076f, 13.7933f, 9.6331f, 11.5409f, 10.6835f)
                close()
            }
        }
        .build()
        return _steemit!!
    }

private var _steemit: ImageVector? = null
