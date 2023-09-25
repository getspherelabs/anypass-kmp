package io.spherelabs.authdomain

interface NameValidation {
  fun execute(name: String): Boolean
}

class ValidateName : NameValidation {

  override fun execute(name: String): Boolean {
    return name.length in 3..15 && name.all { it.isLetter() }
  }
}
