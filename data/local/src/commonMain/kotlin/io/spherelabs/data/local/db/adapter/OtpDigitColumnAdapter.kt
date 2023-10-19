package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.OtpDigitEntity

class OtpDigitColumnAdapter : ColumnAdapter<OtpDigitEntity, String> {
    private val currentValue by nonSynchronizedLazy {
        OtpDigitEntity.values().associateBy { OtpDigitEntity::class }
    }

    override fun decode(databaseValue: String): OtpDigitEntity {
            return currentValue.getValue(databaseValue)
    }

    override fun encode(value: OtpDigitEntity): String {
        TODO("Not yet implemented")
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
