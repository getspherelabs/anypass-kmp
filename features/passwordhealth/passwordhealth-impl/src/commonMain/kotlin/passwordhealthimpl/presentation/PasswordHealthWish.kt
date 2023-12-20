package passwordhealthimpl.presentation

import domain.model.PasswordStats

sealed interface PasswordHealthWish {
    object StartLoadingPasswordStats: PasswordHealthWish
    data class PasswordStatsChanged(val stats: List<PasswordStats>): PasswordHealthWish
}
