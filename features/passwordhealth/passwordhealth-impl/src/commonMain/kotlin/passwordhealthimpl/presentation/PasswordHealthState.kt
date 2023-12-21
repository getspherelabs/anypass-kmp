package passwordhealthimpl.presentation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import domain.model.PasswordHealth
import domain.model.PasswordStats

@Immutable
data class PasswordHealthState(
    val stats: List<PasswordStats> = emptyList(),
    val passwords: List<PasswordHealth> = emptyList(),
    val isNotAvailable: Boolean = false,
    val currentProgress: Float = 0F,
) {
    companion object {
        val Empty = PasswordHealthState()
    }

    fun toggleNotAvailable(): Boolean {
        return !isNotAvailable
    }
}
