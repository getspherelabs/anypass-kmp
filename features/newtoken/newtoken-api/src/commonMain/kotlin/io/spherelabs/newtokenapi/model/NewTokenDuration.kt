package io.spherelabs.newtokenapi.model

enum class NewTokenDuration(
    val value: Long,
) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60);

    companion object {
        fun fromInt(value: Int): NewTokenDuration {
            return when (value) {
                15 -> NewTokenDuration.FIFTEEN
                30 -> NewTokenDuration.THIRTY
                60 -> NewTokenDuration.SIXTY
                else -> {
                    NewTokenDuration.FIFTEEN
                }
            }
        }
    }
}
