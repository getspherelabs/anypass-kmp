package io.spherelabs.accountapi.domain.usecase

interface GetFingerPrintUseCase {
  suspend fun execute(): Boolean
}
