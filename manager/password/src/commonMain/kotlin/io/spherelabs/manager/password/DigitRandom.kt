package io.spherelabs.manager.password

class DigitRandom : LockerRandom {

    override fun generate(length: Int): String {
        return (1..length).map {
            ('0'..'9').random()
        }.joinToString(" ")
    }
}