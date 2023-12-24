package io.spherelabs.designsystem.animation

typealias Intensity = Float

typealias Rotation = Float

data class ShakeConfig(
    val iterations: Int,
    val intensity: Intensity = INTENSITY_VALUE,
    val rotate: Rotation = ZERO_FLOAT,
    val rotateX: Rotation = ZERO_FLOAT,
    val rotateY: Rotation = ZERO_FLOAT,
    val scaleX: Float = ZERO_FLOAT,
    val scaleY: Float = ZERO_FLOAT,
    val translateX: Float = ZERO_FLOAT,
    val translateY: Float = ZERO_FLOAT,
//    val trigger: Long = Cloc,
) {
  companion object {
    private const val ZERO_FLOAT = 0F
    private const val INTENSITY_VALUE = 100_000F
  }
}
