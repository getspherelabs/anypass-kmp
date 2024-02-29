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

public val SocialMediaIcons.Tumblr: ImageVector
    get() {
        if (_tumblr != null) {
            return _tumblr!!
        }
        _tumblr = Builder(name = "Tumblr", defaultWidth = 500.0.dp, defaultHeight = 500.0.dp,
                viewportWidth = 500.0f, viewportHeight = 500.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(50.0f, 0.0f)
                curveTo(22.3f, 0.0f, -0.0f, 22.3f, 0.0f, 50.0f)
                lineTo(0.0f, 450.0f)
                curveTo(0.0f, 477.7f, 22.3f, 500.0f, 50.0f, 500.0f)
                lineTo(450.0f, 500.0f)
                curveTo(477.7f, 500.0f, 500.0f, 477.7f, 500.0f, 450.0f)
                lineTo(500.0f, 50.0f)
                curveTo(500.0f, 22.3f, 477.7f, -0.0f, 450.0f, 0.0f)
                lineTo(50.0f, 0.0f)
                close()
                moveTo(213.125f, 53.156f)
                lineTo(269.375f, 53.156f)
                lineTo(269.375f, 144.656f)
                lineTo(360.875f, 144.656f)
                lineTo(360.875f, 215.313f)
                lineTo(269.375f, 215.313f)
                lineTo(269.375f, 316.75f)
                curveTo(269.375f, 339.703f, 269.111f, 352.965f, 271.531f, 359.469f)
                curveTo(273.951f, 365.941f, 280.001f, 372.656f, 286.594f, 376.531f)
                curveTo(295.373f, 381.789f, 305.353f, 384.406f, 316.625f, 384.406f)
                curveTo(336.66f, 384.406f, 356.588f, 377.891f, 376.406f, 364.875f)
                lineTo(376.406f, 427.281f)
                curveTo(359.523f, 435.241f, 344.208f, 440.848f, 330.5f, 444.031f)
                curveTo(316.776f, 447.215f, 301.966f, 448.844f, 286.031f, 448.844f)
                curveTo(267.942f, 448.844f, 257.26f, 446.542f, 243.375f, 442.0f)
                curveTo(229.466f, 437.401f, 217.613f, 430.87f, 207.813f, 422.469f)
                curveTo(197.964f, 414.003f, 191.17f, 405.011f, 187.375f, 395.5f)
                curveTo(183.572f, 385.965f, 181.688f, 372.152f, 181.688f, 354.063f)
                lineTo(181.688f, 215.313f)
                lineTo(127.938f, 215.313f)
                lineTo(127.938f, 159.25f)
                curveTo(143.478f, 154.209f, 161.469f, 146.985f, 172.531f, 137.563f)
                curveTo(183.642f, 128.108f, 192.561f, 116.788f, 199.25f, 103.563f)
                curveTo(205.987f, 90.369f, 210.592f, 73.553f, 213.125f, 53.156f)
                close()
            }
        }
        .build()
        return _tumblr!!
    }

private var _tumblr: ImageVector? = null
