package io.spherelabs.accountdomain.usecase

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface GetFingerPrintUseCase {
  suspend fun execute(): Boolean
}

class DefaultGetFingerPrintUseCase(
  private val setting: FingerPrintSetting,
) : GetFingerPrintUseCase {

  override suspend fun execute(): Boolean {
    return setting.getFingerPrint()
  }
}
