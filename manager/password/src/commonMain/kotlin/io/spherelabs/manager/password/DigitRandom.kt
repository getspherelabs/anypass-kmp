package io.spherelabs.manager.password

class DigitRandom {
    fun generate(length: Int): String {
        return if (length == 0) "" else (1..length).map { ('0'..'9').random() }.joinToString("")
    }
}
