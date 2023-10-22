package io.spherelabs.masterpassworddomain.usecase

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface GetFingerprintStatusUseCase {
    suspend fun execute(): Boolean
}

class DefaultGetFingerprintStatusUseCase(
    private val prefs: FingerPrintSetting,
) : GetFingerprintStatusUseCase {

    override suspend fun execute(): Boolean {
        return prefs.getFingerPrint()
    }
}
