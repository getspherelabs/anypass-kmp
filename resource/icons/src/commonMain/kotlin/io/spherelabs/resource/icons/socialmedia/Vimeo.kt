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

public val SocialMediaIcons.`Vimeo`: ImageVector
    get() {
        if (`_Vimeo` != null) {
            return `_Vimeo`!!
        }
        `_Vimeo` = Builder(name = "Vimeo", defaultWidth
                = 512.0.dp, defaultHeight = 512.0.dp, viewportWidth = 512.0f, viewportHeight =
                512.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(460.8f, 512.0f)
                lineToRelative(-409.6f, 0.0f)
                curveToRelative(-28.279f, 0.0f, -51.2f, -22.924f, -51.2f, -51.2f)
                lineToRelative(0.0f, -409.6f)
                curveToRelative(0.0f, -28.275f, 22.921f, -51.2f, 51.2f, -51.2f)
                lineToRelative(409.6f, 0.0f)
                curveToRelative(28.276f, 0.0f, 51.2f, 22.925f, 51.2f, 51.2f)
                lineToRelative(0.0f, 409.6f)
                curveToRelative(0.0f, 28.245f, -22.875f, 51.151f, -51.2f, 51.2f)
                close()
                moveTo(430.818f, 170.031f)
                curveToRelative(-1.646f, 36.011f, -26.798f, 85.321f, -75.46f, 147.913f)
                curveToRelative(-50.304f, 65.365f, -92.868f, 98.056f, -127.676f, 98.056f)
                curveToRelative(-21.572f, 0.0f, -39.825f, -19.895f, -54.733f, -59.716f)
                curveToRelative(-9.954f, -36.501f, -19.899f, -72.998f, -29.862f, -109.5f)
                curveToRelative(-11.072f, -39.795f, -22.946f, -59.72f, -35.648f, -59.72f)
                curveToRelative(-2.769f, 0.0f, -12.454f, 5.824f, -29.039f, 17.425f)
                lineToRelative(-17.404f, -22.417f)
                curveToRelative(18.262f, -16.043f, 36.271f, -32.081f, 53.991f, -48.15f)
                curveToRelative(24.354f, -21.034f, 42.649f, -32.102f, 54.835f, -33.22f)
                curveToRelative(28.787f, -2.769f, 46.511f, 16.913f, 53.163f, 59.038f)
                curveToRelative(7.189f, 45.453f, 12.168f, 73.724f, 14.959f, 84.783f)
                curveToRelative(8.311f, 37.717f, 17.446f, 56.555f, 27.413f, 56.555f)
                curveToRelative(7.735f, 0.0f, 19.375f, -12.233f, 34.893f, -36.707f)
                curveToRelative(15.484f, -24.452f, 23.782f, -43.063f, 24.904f, -55.855f)
                curveToRelative(2.215f, -21.111f, -6.092f, -31.688f, -24.904f, -31.688f)
                curveToRelative(-8.862f, 0.0f, -17.997f, 2.035f, -27.392f, 6.063f)
                curveToRelative(18.189f, -59.55f, 52.932f, -88.47f, 104.205f, -86.823f)
                curveToRelative(38.02f, 1.118f, 55.953f, 25.771f, 53.755f, 73.963f)
                close()
            }
        }
        .build()
        return `_Vimeo`!!
    }

private var `_Vimeo`: ImageVector? = null
