package io.spherelabs.data.local.enum

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isSameAs
import io.spherelabs.data.local.db.adapter.nonSynchronizedLazy
import io.spherelabs.data.local.db.otp.OtpDigitEntity
import kotlin.test.Test

class OtpDigitTest {

    private val currentValue by nonSynchronizedLazy {
        OtpDigitEntity
    }

    @Test
    fun `should calculate correct the OTP duration`() {
        val digit = 6L
        val result = currentValue(digit)?.number

        assertThat(result).isSameAs(digit)
    }

    @Test
    fun `should the otp duration type returns correct value`() {
        val digit = OtpDigitEntity.SIX
        val result = currentValue(6L)

        assertThat(result).isEqualTo(digit)
    }

}
