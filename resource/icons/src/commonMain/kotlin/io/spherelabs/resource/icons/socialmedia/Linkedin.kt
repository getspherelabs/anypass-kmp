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

public val SocialMediaIcons.`Linkedin`: ImageVector
    get() {
        if (`_Linkedin` != null) {
            return `_Linkedin`!!
        }
        `_Linkedin` = Builder(name =
                "Linkedin", defaultWidth = 512.0.dp,
                defaultHeight = 512.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(473.305f, -1.353f)
                curveToRelative(20.88f, 0.0f, 37.885f, 16.533f, 37.885f, 36.926f)
                lineToRelative(0.0f, 438.251f)
                curveToRelative(0.0f, 20.393f, -17.005f, 36.954f, -37.885f, 36.954f)
                lineToRelative(-436.459f, 0.0f)
                curveToRelative(-20.839f, 0.0f, -37.773f, -16.561f, -37.773f, -36.954f)
                lineToRelative(0.0f, -438.251f)
                curveToRelative(0.0f, -20.393f, 16.934f, -36.926f, 37.773f, -36.926f)
                lineToRelative(436.459f, 0.0f)
                close()
                moveTo(435.476f, 435.036f)
                lineToRelative(0.0f, -134.034f)
                curveToRelative(0.0f, -65.822f, -14.212f, -116.427f, -91.12f, -116.427f)
                curveToRelative(-36.955f, 0.0f, -61.739f, 20.263f, -71.867f, 39.476f)
                lineToRelative(-1.04f, 0.0f)
                lineToRelative(0.0f, -33.411f)
                lineToRelative(-72.811f, 0.0f)
                lineToRelative(0.0f, 244.396f)
                lineToRelative(75.866f, 0.0f)
                lineToRelative(0.0f, -120.878f)
                curveToRelative(0.0f, -31.883f, 6.031f, -62.773f, 45.554f, -62.773f)
                curveToRelative(38.981f, 0.0f, 39.468f, 36.461f, 39.468f, 64.802f)
                lineToRelative(0.0f, 118.849f)
                lineToRelative(75.95f, 0.0f)
                close()
                moveTo(150.987f, 190.64f)
                lineToRelative(-76.034f, 0.0f)
                lineToRelative(0.0f, 244.396f)
                lineToRelative(76.034f, 0.0f)
                lineToRelative(0.0f, -244.396f)
                close()
                moveTo(112.99f, 69.151f)
                curveToRelative(-24.395f, 0.0f, -44.066f, 19.735f, -44.066f, 44.047f)
                curveToRelative(0.0f, 24.318f, 19.671f, 44.052f, 44.066f, 44.052f)
                curveToRelative(24.299f, 0.0f, 44.026f, -19.734f, 44.026f, -44.052f)
                curveToRelative(0.0f, -24.312f, -19.727f, -44.047f, -44.026f, -44.047f)
                close()
            }
        }
        .build()
        return `_Linkedin`!!
    }

private var `_Linkedin`: ImageVector? = null
