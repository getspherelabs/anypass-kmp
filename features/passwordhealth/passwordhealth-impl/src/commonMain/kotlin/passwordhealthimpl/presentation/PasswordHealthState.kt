package passwordhealthimpl.presentation

import androidx.compose.runtime.Stable
import domain.model.PasswordHealth
import domain.model.PasswordStats

@Stable
data class PasswordHealthState(
    val stats: List<PasswordStats> = emptyList(),
    val passwords: List<PasswordHealth> = emptyList(),
) {
    companion object {
        val Empty = PasswordHealthState()
    }
}
