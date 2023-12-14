package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.AlgorithmTypeEntity

class OtpTypeColumnAdapter: ColumnAdapter<AlgorithmTypeEntity, String> {

    private val currentValue by nonSynchronizedLazy {
        AlgorithmTypeEntity.values().associateBy { it.name }
    }
    override fun decode(databaseValue: String): AlgorithmTypeEntity {
        return currentValue.getValue(databaseValue)
    }

    override fun encode(value: AlgorithmTypeEntity): String {
        return value.name
    }
}
