package domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetPasswordProgressUseCase {
    fun execute(): Flow<Int>
}
