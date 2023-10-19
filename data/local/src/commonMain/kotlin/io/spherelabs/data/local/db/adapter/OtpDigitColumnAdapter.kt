package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.OtpDigitEntity

class OtpDigitColumnAdapter : ColumnAdapter<OtpDigitEntity, Long> {

    private val currentValue by nonSynchronizedLazy {
        OtpDigitEntity
    }
    override fun decode(databaseValue: Long): OtpDigitEntity {
            return currentValue(databaseValue) ?: OtpDigitEntity.SIX
    }

    override fun encode(value: OtpDigitEntity): Long {
        return value.number
    }
}

/**
 * Creates a lazily initialized property using a non-synchronized initializer.
 *
 * @param initializer Lambda that computes the initial value of the property.
 * @return Lazy property with non-synchronized initialization.
 */
inline fun <T> nonSynchronizedLazy(noinline initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, initializer)
}
