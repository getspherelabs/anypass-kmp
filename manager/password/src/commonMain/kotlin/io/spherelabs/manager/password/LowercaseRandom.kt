package io.spherelabs.manager.password

class LowercaseRandom {
    fun generate(): String {
        return (1..10).map { ('a'..'z').random() }.joinToString("")
    }
}