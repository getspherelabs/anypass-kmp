package io.spherelabs.accountapi.domain.usecase


interface SetFingerPrintUseCase {
  suspend fun execute(newValue: Boolean)
}

