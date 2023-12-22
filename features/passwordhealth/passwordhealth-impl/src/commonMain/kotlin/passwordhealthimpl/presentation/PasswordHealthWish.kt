package passwordhealthimpl.presentation

import domain.model.PasswordHealth
import domain.model.PasswordStats

sealed interface PasswordHealthWish {
    object StartLoadingPasswordStats : PasswordHealthWish
    data class LoadedPasswordStats(val stats: List<PasswordStats>) : PasswordHealthWish
    data class NotAvailablePasswords(val message: String) : PasswordHealthWish
    object StartLoadingPasswords : PasswordHealthWish
    data class LoadedPasswords(val passwords: List<PasswordHealth>) : PasswordHealthWish
    object StartLoadingPasswordProgress : PasswordHealthWish
    data class LoadedPasswordProgress(val newProgress: Int) : PasswordHealthWish
}
