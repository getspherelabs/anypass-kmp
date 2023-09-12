package io.spherelabs.manager.password

interface LockerRandom {
    fun generate(length: Int): String
}