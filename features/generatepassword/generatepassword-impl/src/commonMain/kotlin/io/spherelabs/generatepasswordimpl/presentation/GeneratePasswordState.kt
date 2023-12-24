package io.spherelabs.generatepasswordimpl.presentation

import androidx.compose.runtime.Immutable
import io.spherelabs.common.Empty

@Immutable
data class GeneratePasswordState(
    val password: String = String.Empty,
    val uppercaseLength: Float = 0f,
    val digitLength: Float = 0f,
    val specialLength: Float = 0f,
    var length: Int = TOTAL_LENGTH_OF_PASSWORD,
) {
    companion object {
        const val TOTAL_LENGTH_OF_PASSWORD = 10

        val Empty = GeneratePasswordState()
    }

    fun isPasswordValid(): Boolean {
        return password.isNotEmpty()
    }
}
