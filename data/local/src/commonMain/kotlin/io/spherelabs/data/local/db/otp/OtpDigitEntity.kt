package io.spherelabs.data.local.db.otp

enum class OtpDigitEntity(val number: Long) {
    SIX(6),
    EIGHT(8);

    companion object {
        operator fun invoke(number: Long): OtpDigitEntity? {
            return fromRaw(number)
        }

        private fun fromRaw(number: Long): OtpDigitEntity? {
            return values().find { it.number == number }
        }
    }
}
