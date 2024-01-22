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

public val SocialMediaIcons.`Facebook`: ImageVector
    get() {
        if (`_Facebook` != null) {
            return `_Facebook`!!
        }
        `_Facebook` = Builder(name =
                "Facebook", defaultWidth =
                512.0.dp, defaultHeight = 512.0.dp, viewportWidth = 512.0f, viewportHeight =
                512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(449.446f, 0.0f)
                curveToRelative(34.525f, 0.0f, 62.554f, 28.03f, 62.554f, 62.554f)
                lineToRelative(0.0f, 386.892f)
                curveToRelative(0.0f, 34.524f, -28.03f, 62.554f, -62.554f, 62.554f)
                lineToRelative(-106.468f, 0.0f)
                lineToRelative(0.0f, -192.915f)
                lineToRelative(66.6f, 0.0f)
                lineToRelative(12.672f, -82.621f)
                lineToRelative(-79.272f, 0.0f)
                lineToRelative(0.0f, -53.617f)
                curveToRelative(0.0f, -22.603f, 11.073f, -44.636f, 46.58f, -44.636f)
                lineToRelative(36.042f, 0.0f)
                lineToRelative(0.0f, -70.34f)
                curveToRelative(0.0f, 0.0f, -32.71f, -5.582f, -63.982f, -5.582f)
                curveToRelative(-65.288f, 0.0f, -107.96f, 39.569f, -107.96f, 111.204f)
                lineToRelative(0.0f, 62.971f)
                lineToRelative(-72.573f, 0.0f)
                lineToRelative(0.0f, 82.621f)
                lineToRelative(72.573f, 0.0f)
                lineToRelative(0.0f, 192.915f)
                lineToRelative(-191.104f, 0.0f)
                curveToRelative(-34.524f, 0.0f, -62.554f, -28.03f, -62.554f, -62.554f)
                lineToRelative(0.0f, -386.892f)
                curveToRelative(0.0f, -34.524f, 28.029f, -62.554f, 62.554f, -62.554f)
                lineToRelative(386.892f, 0.0f)
                close()
            }
        }
        .build()
        return `_Facebook`!!
    }

private var `_Facebook`: ImageVector? = null
