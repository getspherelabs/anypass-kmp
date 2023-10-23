package io.spherelabs.data.local.repository

import io.spherelabs.authenticatordomain.model.CounterDomain
import io.spherelabs.authenticatordomain.model.OtpDomain
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.mapper.asDomain
import io.spherelabs.local.db.CounterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultAuthenticatorRepository(
    private val otpDao: OtpDao,
    private val counterDao: CounterDao,
) : AuthenticatorRepository {

    override fun getAllOtp(): Flow<List<OtpDomain>> {
        return otpDao.getAllOtp().map { allOtp -> allOtp.map { otp -> otp.asDomain() } }
    }

    override fun getCounters(): Flow<List<CounterDomain>> {
        return counterDao.getCounters().map { counters ->
            counters.map { counter: CounterEntity -> counter.asDomain() }
        }
    }

    override suspend fun incrementCounter(id: String) {
        withContext(Dispatchers.IO) {
            counterDao.incrementCounter(id)
        }
    }
}
