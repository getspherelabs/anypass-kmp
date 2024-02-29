package io.spherelabs.resource.icons.socialmedia

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.spherelabs.resource.icons.SocialMediaIcons

public val SocialMediaIcons.`Messenger`: ImageVector
    get() {
        if (`_Messenger` != null) {
            return `_Messenger`!!
        }
        `_Messenger` = Builder(name =
                "Messenger", defaultWidth = 512.0.dp,
                defaultHeight = 512.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(449.446f, 0.0f)
                curveToRelative(34.525f, 0.0f, 62.554f, 28.03f, 62.554f, 62.554f)
                lineToRelative(0.0f, 386.892f)
                curveToRelative(0.0f, 34.524f, -28.03f, 62.554f, -62.554f, 62.554f)
                lineToRelative(-386.892f, 0.0f)
                curveToRelative(-34.524f, 0.0f, -62.554f, -28.03f, -62.554f, -62.554f)
                lineToRelative(0.0f, -386.892f)
                curveToRelative(0.0f, -34.524f, 28.029f, -62.554f, 62.554f, -62.554f)
                lineToRelative(386.892f, 0.0f)
                close()
                moveTo(256.0f, 75.784f)
                curveToRelative(-104.225f, 0.0f, -185.0f, 76.346f, -185.0f, 179.455f)
                curveToRelative(0.0f, 53.937f, 22.111f, 100.54f, 58.106f, 132.739f)
                curveToRelative(3.014f, 2.716f, 4.839f, 6.476f, 4.988f, 10.533f)
                lineToRelative(1.005f, 32.906f)
                curveToRelative(0.335f, 10.497f, 11.166f, 17.309f, 20.771f, 13.102f)
                lineToRelative(36.701f, -16.191f)
                curveToRelative(3.127f, -1.377f, 6.589f, -1.638f, 9.865f, -0.745f)
                curveToRelative(16.861f, 4.652f, 34.841f, 7.11f, 53.564f, 7.11f)
                curveToRelative(104.225f, 0.0f, 185.0f, -76.346f, 185.0f, -179.454f)
                curveToRelative(0.0f, -103.074f, -80.721f, -179.403f, -185.0f, -179.455f)
                close()
                moveTo(144.926f, 307.724f)
                lineToRelative(54.345f, -86.21f)
                curveToRelative(8.636f, -13.697f, 27.174f, -17.122f, 40.127f, -7.407f)
                lineToRelative(43.216f, 32.421f)
                curveToRelative(3.983f, 2.979f, 9.418f, 2.942f, 13.364f, -0.036f)
                lineToRelative(58.366f, -44.297f)
                curveToRelative(7.779f, -5.917f, 17.979f, 3.425f, 12.73f, 11.689f)
                lineToRelative(-54.309f, 86.172f)
                curveToRelative(-8.636f, 13.698f, -27.172f, 17.122f, -40.126f, 7.407f)
                lineToRelative(-43.216f, -32.421f)
                curveToRelative(-3.984f, -2.979f, -9.418f, -2.941f, -13.363f, 0.037f)
                lineToRelative(-58.404f, 44.333f)
                curveToRelative(-7.753f, 5.898f, -17.911f, -3.362f, -12.73f, -11.688f)
                close()
            }
        }
        .build()
        return `_Messenger`!!
    }

private var `_Messenger`: ImageVector? = null
