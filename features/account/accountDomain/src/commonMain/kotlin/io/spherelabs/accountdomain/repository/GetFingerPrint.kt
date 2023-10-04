package io.spherelabs.accountdomain.repository

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface GetFingerPrint {
    suspend fun execute(): Boolean
}

class DefaultGetFingerPrint(
    private val setting: FingerPrintSetting,
) : GetFingerPrint {

    override suspend fun execute(): Boolean {
        return setting.getFingerPrint()
    }
}
