package io.spherelabs.manager.password

class SpecialRandom {
    fun generate(length: Int): String {
        return if (length == 0) "" else (1..length).map {
            listOf(
                '!',
                '@',
                '#',
                '$',
                '%',
                '&',
                '*',
                '+',
                '=',
                '-',
                '~',
                '?',
                '/',
                '_'
            ).random()
        }.joinToString("")
    }
}