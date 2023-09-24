package io.spherelabs.masterpassworddomain

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface SetMasterPassword {
    suspend fun execute(value: String)
}

class DefaultSetMasterPassword(
    private val prefs: MasterPasswordSetting
) : SetMasterPassword {

    override suspend fun execute(value: String) {
        prefs.setMasterPassword(value)
    }
}