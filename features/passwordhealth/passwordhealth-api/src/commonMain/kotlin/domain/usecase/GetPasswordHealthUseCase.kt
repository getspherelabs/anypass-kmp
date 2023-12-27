package domain.usecase

import domain.model.PasswordHealth
import kotlinx.coroutines.flow.Flow

interface GetPasswordHealthUseCase {
  fun execute(): Flow<List<PasswordHealth>>
}
