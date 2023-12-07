package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.mapper.asEntity
import io.spherelabs.local.db.CounterEntity
import io.spherelabs.newtokenapi.domain.repository.NewTokenRepository
import io.spherelabs.newtokenapi.model.NewTokenDomain
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
            runCatching {
                otpDao.insertOtp(newEntity)
            }.onSuccess {
                counterDao.insertCounter(
                    CounterEntity(
                        newEntity.id,
                        counter,
                    ),
                )
            }

        }
    }
}

