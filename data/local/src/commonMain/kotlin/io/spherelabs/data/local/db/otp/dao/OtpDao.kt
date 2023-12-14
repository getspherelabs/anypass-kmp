package io.spherelabs.data.local.db.otp.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface OtpDao {
    fun getAllOtp(): Flow<List<OtpEntity>>
    fun getCounterByAccountId(accountId: String): Flow<OtpEntity>
    fun insertOtp(entity: OtpEntity)
}

class DefaultOtpDao(
    database: AnyPassDatabase,
) : OtpDao {

    private val queries = database.otpQueries

    override fun getAllOtp(): Flow<List<OtpEntity>> {
        return queries.getAllOtp().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getCounterByAccountId(accountId: String): Flow<OtpEntity> {
        return queries.getOtpById(accountId).asFlow().mapToOne(Dispatchers.IO)
    }

    override fun insertOtp(entity: OtpEntity) {
        queries.transaction {
            queries.insertOtp(
                id = entity.id,
                type = entity.type,
                createdTimestamp = entity.createdTimestamp,
                digit = entity.digit,
                info = entity.info,
                issuer = entity.issuer,
                secret = entity.secret,
                duration = entity.duration,
                serviceName = entity.serviceName,
            )
        }
    }
}
