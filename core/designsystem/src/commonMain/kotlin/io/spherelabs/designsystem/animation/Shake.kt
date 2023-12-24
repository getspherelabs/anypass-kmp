package io.spherelabs.designsystem.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

fun Modifier.shake(controller: ShakeController) = composed {
  controller.shakeConfig?.let { shakeConfig ->
    val shake = remember { Animatable(SHAKE_INITIAL_VALUE) }
    LaunchedEffect(controller.shakeConfig) {
      for (i in 0..shakeConfig.iterations) {
        when (i % 2) {
          0 -> shake.animateTo(1f, spring(stiffness = shakeConfig.intensity))
          else -> shake.animateTo(-1f, spring(stiffness = shakeConfig.intensity))
        }
      }
      shake.animateTo(SHAKE_TARGET_VALUE)
    }
    this.rotate(shake.value * shakeConfig.rotate)
        .graphicsLayer {
          rotationX = shake.value * shakeConfig.rotateX
          rotationY = shake.value * shakeConfig.rotateY
        }
        .scale(
            scaleX = 1f + (shake.value * shakeConfig.scaleX),
            scaleY = 1f + (shake.value * shakeConfig.scaleY),
        )
        .offset {
          intOffset(
              shakeValue = shake.value,
              translateX = shakeConfig.translateX,
              translateY = shakeConfig.translateY,
          )
        }
  }
      ?: this
}

private fun intOffset(shakeValue: Float, translateX: Float, translateY: Float): IntOffset {
  return IntOffset(
      calculateX(shakeValue, translateX),
      calculateY(shakeValue, translateY),
  )
}

private fun calculateX(shakeValue: Float, translateX: Float): Int {
  return (shakeValue * translateX).roundToInt()
}

private fun calculateY(shakeValue: Float, translateY: Float): Int {
  return (shakeValue * translateY).roundToInt()
}

private const val SHAKE_INITIAL_VALUE = 0F
private const val SHAKE_TARGET_VALUE = 0F
