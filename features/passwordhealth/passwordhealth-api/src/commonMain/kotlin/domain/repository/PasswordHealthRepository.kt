package domain.repository

import domain.model.PasswordHealth
import kotlinx.coroutines.flow.Flow

interface PasswordHealthRepository {
    fun getSizeOfStrongPasswords(): Flow<Int>
    fun getSizeOfWeakPasswords(): Flow<Int>
    fun getTotalPasswords(): Flow<Int>
    fun getSizeOfReusedPasswords(): Flow<Int>
    fun getCurrentPasswordHealth(): Flow<Double>
    fun getWeakPasswords(): Flow<List<PasswordHealth>>
    fun getStrongPasswords(): Flow<List<PasswordHealth>>
    fun getReusedPasswords(): Flow<List<PasswordHealth>>
}
