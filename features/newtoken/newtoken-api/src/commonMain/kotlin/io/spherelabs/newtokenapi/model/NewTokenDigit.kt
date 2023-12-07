package io.spherelabs.newtokenapi.model

enum class NewTokenDigit(val number: Long) {
    SIX(6),
    EIGHT(8);

    companion object {
        fun fromInt(value: Int): NewTokenDigit {
            return when (value) {
                6 -> NewTokenDigit.SIX
                8 -> NewTokenDigit.EIGHT
                else -> NewTokenDigit.SIX
            }
        }
    }
}
