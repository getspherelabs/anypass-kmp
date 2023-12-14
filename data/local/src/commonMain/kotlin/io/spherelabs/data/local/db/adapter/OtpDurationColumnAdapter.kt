package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.OtpDurationEntity

class OtpDurationColumnAdapter: ColumnAdapter<OtpDurationEntity, Long> {

    private val currentValue by nonSynchronizedLazy {
        OtpDurationEntity.values().associateBy { it.value }
    }

    override fun decode(databaseValue: Long): OtpDurationEntity {
        return currentValue.getValue(databaseValue)
    }

    override fun encode(value: OtpDurationEntity): Long {
        return value.value
    }
}
