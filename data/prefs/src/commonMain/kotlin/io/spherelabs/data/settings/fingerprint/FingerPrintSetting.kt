package io.spherelabs.data.settings.fingerprint

import com.russhwolf.settings.coroutines.FlowSettings

interface FingerPrintSetting {
    suspend fun setFingerPrint(newValue: Boolean)

    suspend fun getFingerPrint(): Boolean
}

class DefaultFingerPrintSetting(
    private val prefs: FlowSettings,
) : FingerPrintSetting {

    override suspend fun setFingerPrint(newValue: Boolean) {
        prefs.putBoolean(FINGER_PRINT_PREFS, value = newValue)
    }

    override suspend fun getFingerPrint(): Boolean {
        return prefs.getBoolean(FINGER_PRINT_PREFS, false)
    }

    companion object {
        private const val FINGER_PRINT_PREFS = "finger_print"
    }
}
