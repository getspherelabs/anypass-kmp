package domain.usecase

import domain.model.PasswordStats
import kotlinx.coroutines.flow.Flow

interface GetPasswordStatsUseCase {
  fun execute(): Flow<List<PasswordStats>>
}
