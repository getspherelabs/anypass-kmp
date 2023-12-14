package io.spherelabs.data.local.db.otp.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.CounterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface CounterDao {
    fun getCounters(): Flow<List<CounterEntity>>
    suspend fun insertCounter(entity: CounterEntity)
    suspend fun incrementCounter(otpId: String)
}

class DefaultCounterDao(
    database: AnyPassDatabase,
) : CounterDao {

    private val queries = database.counterQueries

    override fun getCounters(): Flow<List<CounterEntity>> {
       return queries.getCounters().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun insertCounter(entity: CounterEntity) {
        queries.transaction {
            println("Entity is $entity")
            queries.insertCounter(
                otpId = entity.otpId,
                counter = entity.counter
            )
        }
    }

    override suspend fun incrementCounter(otpId: String) {
        queries.transaction {
            queries.incrementCounter(otpId)
        }
    }
}
