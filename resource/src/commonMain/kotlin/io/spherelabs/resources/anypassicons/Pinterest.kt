package io.spherelabs.resources.anypassicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
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

public val AnyPassIcons.Pinterest: ImageVector
    get() {
        if (_pinterest != null) {
            return _pinterest!!
        }
        _pinterest = Builder(name = "Pinterest", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFFF0000)), stroke = null, strokeLineWidth = 0.0f,
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
                    pathFillType = NonZero) {
                moveTo(11.413f, 6.0402f)
                curveTo(9.4651f, 6.2628f, 7.524f, 7.8761f, 7.4439f, 10.1807f)
                curveTo(7.3935f, 11.5878f, 7.7838f, 12.6434f, 9.092f, 12.9398f)
                curveTo(9.6596f, 11.9147f, 8.9088f, 11.6885f, 8.7921f, 10.9469f)
                curveTo(8.3126f, 7.9077f, 12.2165f, 5.8351f, 14.2594f, 7.9569f)
                curveTo(15.6729f, 9.4262f, 14.7424f, 13.9462f, 12.4625f, 13.4764f)
                curveTo(10.2788f, 13.0277f, 13.5315f, 9.4297f, 11.7884f, 8.7232f)
                curveTo(10.3715f, 8.1491f, 9.6184f, 10.4794f, 10.2903f, 11.637f)
                curveTo(9.8966f, 13.6276f, 9.0485f, 15.5033f, 9.3918f, 18.0f)
                curveTo(10.5054f, 17.1729f, 10.8808f, 15.5889f, 11.1887f, 13.9369f)
                curveTo(11.7484f, 14.2848f, 12.0471f, 14.6469f, 12.7613f, 14.7031f)
                curveTo(15.3948f, 14.9117f, 16.8654f, 12.0119f, 16.5061f, 9.3371f)
                curveTo(16.1868f, 6.9658f, 13.8749f, 5.759f, 11.413f, 6.0402f)
                close()
            }
        }
        .build()
        return _pinterest!!
    }

private var _pinterest: ImageVector? = null
