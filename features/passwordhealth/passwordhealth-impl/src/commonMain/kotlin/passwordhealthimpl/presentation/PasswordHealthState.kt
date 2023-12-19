package passwordhealthimpl.presentation

import androidx.compose.runtime.Stable
import domain.model.PasswordHealth
import domain.model.PasswordStats

@Stable
data class PasswordHealthState(
    val stats: PasswordStats? = null,
    val passwords: List<PasswordHealth> = emptyList(),
) {
    companion object {
        val Empty = PasswordHealthState()
    }
}
