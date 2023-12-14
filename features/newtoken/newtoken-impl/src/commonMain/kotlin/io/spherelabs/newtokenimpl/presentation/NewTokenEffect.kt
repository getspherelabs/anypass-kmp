package io.spherelabs.newtokenimpl.presentation

sealed interface NewTokenEffect {
    data class Failure(val message: String) : NewTokenEffect
    data class Info(val message: String) : NewTokenEffect
}
