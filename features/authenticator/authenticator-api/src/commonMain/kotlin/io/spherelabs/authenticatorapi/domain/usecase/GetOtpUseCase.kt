package io.spherelabs.authenticatorapi.domain.usecase

import io.spherelabs.authenticatorapi.model.OtpDomain
import kotlinx.coroutines.flow.Flow

interface GetOtpUseCase {
  fun execute(): Flow<List<OtpDomain>>
}
