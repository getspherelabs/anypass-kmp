package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.mapper.asEntity
import io.spherelabs.local.db.CounterEntity
import io.spherelabs.newtokendomain.model.NewTokenDomain
import io.spherelabs.newtokendomain.repository.NewTokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DefaultNewTokenRepository (
    private val otpDao: OtpDao,
    private val counterDao: CounterDao,
    ) : NewTokenRepository {

    override suspend fun insertOtpWithCount(otpDomain: NewTokenDomain, counter: Long) {
        withContext(Dispatchers.IO) {
            val newEntity = otpDomain.asEntity()
            counterDao.insertCounter(
                CounterEntity(
                    newEntity.id,
                    counter,
                ),
            )
            otpDao.insertOtp(newEntity)
        }
    }
}

