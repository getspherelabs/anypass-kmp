package io.spherelabs.authenticatorimpl.domain.usecase

import io.spherelabs.authenticatorapi.domain.repository.AuthenticatorRepository
import io.spherelabs.authenticatorapi.domain.usecase.GetOtpUseCase
import io.spherelabs.authenticatorapi.model.OtpDomain
import kotlinx.coroutines.flow.Flow

class DefaultGetOtpUseCase(
    private val authenticatorRepository: AuthenticatorRepository,
) : GetOtpUseCase {

  override fun execute(): Flow<List<OtpDomain>> {
    return authenticatorRepository.getAllOtp()
  }
}
