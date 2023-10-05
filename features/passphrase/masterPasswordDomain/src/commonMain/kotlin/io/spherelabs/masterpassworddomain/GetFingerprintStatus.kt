package io.spherelabs.masterpassworddomain

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface GetFingerprintStatus {
    suspend fun execute(): Boolean
}

class DefaultGetFingerprintStatus(
    private val prefs: FingerPrintSetting,
) : GetFingerprintStatus {

    override suspend fun execute(): Boolean {
        return prefs.getFingerPrint()
    }
}
