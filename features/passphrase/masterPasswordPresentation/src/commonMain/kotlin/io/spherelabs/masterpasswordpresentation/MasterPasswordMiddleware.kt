package io.spherelabs.masterpasswordpresentation

import io.spherelabs.masterpassworddomain.GetFingerprintStatus
import io.spherelabs.masterpassworddomain.GetMasterPassword
import io.spherelabs.masterpassworddomain.IsPasswordExist
import io.spherelabs.masterpassworddomain.SetMasterPassword
import io.spherelabs.meteor.middleware.Middleware

class MasterPasswordMiddleware(
    private val setMasterPassword: SetMasterPassword,
    private val isPasswordExist: IsPasswordExist,
    private val getMasterPassword: GetMasterPassword,
    private val getFingerprintStatus: GetFingerprintStatus,
) : Middleware<MasterPasswordState, MasterPasswordWish> {

    override suspend fun process(
        state: MasterPasswordState,
        wish: MasterPasswordWish,
        next: suspend (MasterPasswordWish) -> Unit,
    ) {
        when (wish) {
            MasterPasswordWish.CheckMasterPassword -> {
                handlePasswordExisted(next)
            }
            is MasterPasswordWish.SetMasterPassword -> {
                handlePassword(wish.password, next)
            }
            MasterPasswordWish.SubmitClicked -> {
                runCatching {
                    if (state.isExistPassword) {
                        val password = getMasterPassword.execute()

                        if (state.password == password) {
                            next.invoke(MasterPasswordWish.NavigateToHome)
                        }
                    } else {
                        next.invoke(MasterPasswordWish.SetMasterPassword(state.password))
                    }
                }
            }
            else -> {}
        }
    }

    private suspend fun handlePassword(
        password: String,
        next: suspend (MasterPasswordWish) -> Unit,
    ) {
        runCatching { setMasterPassword.execute(password) }
            .onSuccess { next.invoke(MasterPasswordWish.SetPasswordSuccessFully) }
            .onFailure {
                val failureMessage = it.message ?: "Error is occurred"
                next.invoke(MasterPasswordWish.SetPasswordFailure(failureMessage))
            }
    }

    private suspend fun handlePasswordExisted(next: suspend (MasterPasswordWish) -> Unit) {
        runCatching { isPasswordExist.execute() }
            .onSuccess { result ->
                if (result) {
                    next.invoke(MasterPasswordWish.PasswordExisted)
                    handleFingerprint(next)
                }
            }
    }

    private suspend fun handleFingerprint(
        next: suspend (MasterPasswordWish) -> Unit,
    ) {
        runCatching {
            getFingerprintStatus.execute()
        }.onSuccess { isEnabled ->
            if (isEnabled) {
                next.invoke(MasterPasswordWish.GetFingerprint(isEnabled))
            }
        }
    }
}
