package io.spherelabs.data.local.db.adapter

import app.cash.sqldelight.ColumnAdapter
import io.spherelabs.data.local.db.otp.AlgorithmTypeEntity

class OtpTypeColumnAdapter: ColumnAdapter<AlgorithmTypeEntity, String> {

    private val currentValue by nonSynchronizedLazy {
        AlgorithmTypeEntity
    }
    override fun decode(databaseValue: String): AlgorithmTypeEntity {
        return currentValue(databaseValue) ?: AlgorithmTypeEntity.SHA1
    }

    override fun encode(value: AlgorithmTypeEntity): String {
        return value.name
    }
}
