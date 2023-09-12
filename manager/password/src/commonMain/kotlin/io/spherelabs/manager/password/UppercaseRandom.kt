package io.spherelabs.manager.password

class UppercaseRandom {

    fun generate(length: Int): String {
        return if (length == 0) "" else (1..length).map { ('A'..'Z').random() }.joinToString("")
    }

}