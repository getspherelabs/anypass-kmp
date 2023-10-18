package io.spherelabs.masterpassworddomain.usecase

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface GetMasterPasswordUseCase {
    suspend fun execute(): String
}

class DefaultGetMasterPasswordUseCase(private val prefs: MasterPasswordSetting) : GetMasterPasswordUseCase {

    override suspend fun execute(): String {
        return prefs.getMasterPassword()
    }
}
