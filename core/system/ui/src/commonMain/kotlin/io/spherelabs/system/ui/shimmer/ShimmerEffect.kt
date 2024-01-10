package io.spherelabs.system.ui.shimmer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.platform.LocalDensity

/**
 * Copied from https://github.com/valentinilk/compose-shimmer/tree/master
 */

@Composable
internal fun rememberShimmerEffect(theme: ShimmerTheme): ShimmerEffect {
    val shimmerWidth = with(LocalDensity.current) { theme.shimmerWidth.toPx() }
    val shimmerEffect = remember(theme) {
        ShimmerEffect(
            animationSpec = theme.animationSpec,
            blendMode = theme.blendMode,
            rotation = theme.rotation,
            shaderColors = theme.shaderColors,
            shaderColorStops = theme.shaderColorStops,
            shimmerWidth = shimmerWidth,
        )
    }

    LaunchedEffect(shimmerEffect) {
        shimmerEffect.startAnimation()
    }
    return shimmerEffect
}

internal class ShimmerEffect(
    private val animationSpec: AnimationSpec<Float>,
    private val blendMode: BlendMode,
    private val rotation: Float,
    private val shaderColors: List<Color>,
    private val shaderColorStops: List<Float>?,
    private val shimmerWidth: Float,
) {

    private val animatedState = Animatable(0f)
    private val transformationMatrix = Matrix()
    private val gradientFrom = Offset(-shimmerWidth / 2, 0f)
    private val gradientTo = -gradientFrom
    private val paint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Fill
        blendMode = this@ShimmerEffect.blendMode
    }

    internal suspend fun startAnimation() {
        animatedState.animateTo(
            targetValue = 1f,
            animationSpec = animationSpec,
        )
    }

    private val emptyPaint = Paint()

    fun ContentDrawScope.draw(shimmerArea: ShimmerArea) = with(shimmerArea) {
        if (shimmerBounds.isEmpty || viewBounds.isEmpty) return

        val progress = animatedState.value
        val traversal = -translationDistance / 2 + translationDistance * progress + pivotPoint.x

        transformationMatrix.apply {
            reset()
            translate(pivotPoint.x, pivotPoint.y, 0f)
            rotateZ(rotation)
            translate(-pivotPoint.x, -pivotPoint.y, 0f)
            translate(traversal, 0f, 0f)
        }

        paint.shader = LinearGradientShader(
            from = transformationMatrix.map(gradientFrom),
            to = transformationMatrix.map(gradientTo),
            colors = shaderColors,
            colorStops = shaderColorStops,
        )

        val drawArea = size.toRect()
        drawIntoCanvas { canvas ->
            canvas.withSaveLayer(
                bounds = drawArea,
                paint = emptyPaint,
            ) {
                drawContent()
                canvas.drawRect(drawArea, paint)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is ShimmerEffect) return false


        if (animationSpec != other.animationSpec) return false
        if (blendMode != other.blendMode) return false
        if (rotation != other.rotation) return false
        if (shaderColors != other.shaderColors) return false
        if (shaderColorStops != other.shaderColorStops) return false
        if (shimmerWidth != other.shimmerWidth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = animationSpec.hashCode()
        result = 31 * result + blendMode.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + shaderColors.hashCode()
        result = 31 * result + shaderColorStops.hashCode()
        result = 31 * result + shimmerWidth.hashCode()
        return result
    }
}
