package io.spherelabs.generatepasswordpresentation

data class GeneratePasswordState(
  val password: String = "",
  val uppercaseLength: Float = 0f,
  val digitLength: Float = 0f,
  var length: Int = 10
) {
  companion object {
    val Empty = GeneratePasswordState()
  }
}
