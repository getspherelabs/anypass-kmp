package io.spherelabs.newtokenpresentation

sealed interface NewTokenEffect {
    data class Failure(val message: String): NewTokenEffect
}
