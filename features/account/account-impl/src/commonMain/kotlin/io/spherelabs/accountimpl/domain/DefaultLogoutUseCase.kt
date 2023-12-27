package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.LogoutUseCase
import io.spherelabs.firebase.FirebaseAuthManager

class DefaultLogoutUseCase(
    private val authManager: FirebaseAuthManager,
) : LogoutUseCase {
    override suspend fun execute(): Result<Boolean> {
        return authManager.logout()
    }
}
