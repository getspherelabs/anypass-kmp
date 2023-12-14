package io.spherelabs.accountapi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetSizeOfWeakPasswordUseCase {
  fun execute(): Flow<Int>
}
