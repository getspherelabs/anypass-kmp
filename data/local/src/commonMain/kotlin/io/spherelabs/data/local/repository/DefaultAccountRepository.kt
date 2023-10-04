package io.spherelabs.data.local.repository

import io.spherelabs.accountdomain.repository.AccountRepository
import io.spherelabs.data.local.db.PasswordDao
import kotlinx.coroutines.flow.Flow


class DefaultAccountRepository(
    private val passwordDao: PasswordDao,
) : AccountRepository {

    override fun getSizeOfStrongPasswords(): Flow<Int> {
        return passwordDao.getSizeOfStrongPasswords()
    }

    override fun getSizeOfWeakPasswords(): Flow<Int> {
        return passwordDao.getSizeOfWeakPasswords()
    }

    override fun getTotalPasswords(): Flow<Int> {
        return passwordDao.getTotalPasswords()
    }
}
