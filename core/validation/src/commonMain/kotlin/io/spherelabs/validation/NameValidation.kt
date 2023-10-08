package io.spherelabs.validation

interface NameValidation {
  fun execute(name: String): Boolean
}

class DefaultNameValidation : NameValidation {

  override fun execute(name: String): Boolean {
    return name.length in 2..20 && name.all { it.isLetter() }
  }
}
