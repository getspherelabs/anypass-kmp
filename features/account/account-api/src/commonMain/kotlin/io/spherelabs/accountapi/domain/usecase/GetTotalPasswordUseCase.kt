package io.spherelabs.accountapi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetTotalPasswordUseCase {
  fun execute(): Flow<Int>
}

