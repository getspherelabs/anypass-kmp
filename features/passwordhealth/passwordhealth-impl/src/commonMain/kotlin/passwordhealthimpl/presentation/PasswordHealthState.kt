package passwordhealthimpl.presentation

import androidx.compose.runtime.Stable
import domain.model.PasswordStats

@Stable
data class PasswordHealthState(
    val stats: PasswordStats? = null,
) {
    companion object {
        val Empty = PasswordHealthState()
    }
}
