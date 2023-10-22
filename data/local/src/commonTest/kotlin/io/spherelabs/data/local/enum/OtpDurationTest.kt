package io.spherelabs.data.local.enum

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isSameAs
import io.spherelabs.data.local.db.adapter.nonSynchronizedLazy
import io.spherelabs.data.local.db.otp.OtpDurationEntity
import kotlin.test.Test

class OtpDurationTest {

    private val currentValue by nonSynchronizedLazy {
        OtpDurationEntity
    }

    @Test
    fun `should calculate correct the OTP duration`() {
        val duration = 15L
        val result = currentValue(duration)?.value

        assertThat(result).isSameAs(duration)
    }

    @Test
    fun `should the otp duration type returns correct value`() {
        val duration = OtpDurationEntity.FIFTEEN
        val result = currentValue(15)

        assertThat(result).isEqualTo(duration)
    }
}
