package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.OtpDurationEntity

class OtpDurationColumnAdapter: ColumnAdapter<OtpDurationEntity, Long> {

    private val currentValue by nonSynchronizedLazy {
        OtpDurationEntity
    }

    override fun decode(databaseValue: Long): OtpDurationEntity {
        return currentValue(databaseValue) ?: OtpDurationEntity.Fifteen
    }

    override fun encode(value: OtpDurationEntity): Long {
        return value.value
    }
}
