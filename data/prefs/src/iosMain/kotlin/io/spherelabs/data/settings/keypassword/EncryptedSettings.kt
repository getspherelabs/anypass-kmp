package io.spherelabs.data.settings.keypassword

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class EncryptedSettings {
    actual val settings: Settings = NSUserDefaultsSettings(
        NSUserDefaults.standardUserDefaults()
    )
}
