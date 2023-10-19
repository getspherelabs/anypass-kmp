package io.spherelabs.data.local.db.otp.dao

import io.spherelabs.local.db.OtpEntity
import kotlinx.coroutines.flow.Flow

interface OtpDao {
    fun getAllOtp(): Flow<List<OtpEntity>>
    suspend fun getCounterByAccountId(accountId: String): Long
}
