package io.spherelabs.accountapi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetSizeOfStrongPasswordUseCase {
  fun execute(): Flow<Int>
}
