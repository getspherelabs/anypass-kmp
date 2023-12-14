package io.spherelabs.designsystem.animation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShakeController {
    var shakeConfig: ShakeConfig? by mutableStateOf(null)
        private set

    fun shake(shakeConfig: ShakeConfig) {
        this.shakeConfig = shakeConfig
    }
}
