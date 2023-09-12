package io.spherelabs.manager.password

import kotlin.test.Test

class RandomTest {

    @Test
    fun `check the random working`() {
        val list = ArrayList<Int>()

        list.add(0)
        list.add(1)
        list.add(2)
        println("Random: ${list.random()}")
    }

    @Test
    fun `check the random password`() {
        val uppercase = 4
        val digits = 2

        val uppercaseLength = 3

        var password = ""

        val list = mutableListOf<Int>()

        list.add(uppercase)
        list.add(digits)

        fun uppercaseRandom(): String {
            return (1 .. uppercaseLength).map {
                ('A'..'Z').random()
            }.joinToString("")
        }

        fun digitsRandom(): String {
            return (1..4).map {
                ('0'..'9').random()
            }.joinToString("")
        }

        fun lowercaseRandom(): String {
            return (1..10).map {
                ('a'..'z').random()
            }.joinToString("")
        }
        password += uppercaseRandom()
        password += lowercaseRandom()
        password += digitsRandom()
        password += listOf('!', '@', '#', '$', '%', '&', '*', '+', '=', '-', '~', '?', '/', '_').random().toString()
        println(uppercaseRandom())
        println(password)
    }
}