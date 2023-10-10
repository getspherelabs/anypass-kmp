package io.spherelabs.masterpasswordpresentation

import io.spherelabs.masterpassworddomain.GetFingerprintStatus
import io.spherelabs.masterpassworddomain.GetMasterPassword
import io.spherelabs.masterpassworddomain.IsPasswordExist
import io.spherelabs.meteor.middleware.Middleware

class MasterPasswordMiddleware(
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
            MasterPasswordWish.SubmitClicked -> {
                runCatching {
                    if (state.isExistPassword) {
                        val password = getMasterPassword.execute()

                        if (state.password == password) {
                            next.invoke(MasterPasswordWish.NavigateToHome)
                        } else {
                            next.invoke(MasterPasswordWish.IsNotMatched("Key password is not match!"))
                        }
                    }
                }
            }
            else -> {}
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
