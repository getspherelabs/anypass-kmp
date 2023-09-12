package io.spherelabs.manager.password

class UppercaseRandom: LockerRandom {

    override fun generate(length: Int): String {
        return (1..length).map {
            ('A'..'Z').random()
        }.joinToString("")
    }

}