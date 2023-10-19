package io.spherelabs.masterpasswordpresentation

import io.spherelabs.masterpassworddomain.usecase.GetFingerprintStatusUseCase
import io.spherelabs.masterpassworddomain.usecase.GetMasterPasswordUseCase
import io.spherelabs.masterpassworddomain.usecase.IsPasswordExistUseCase
import io.spherelabs.meteor.middleware.Middleware

class MasterPasswordMiddleware(
    private val isPasswordExistUseCase: IsPasswordExistUseCase,
    private val getMasterPasswordUseCase: GetMasterPasswordUseCase,
    private val getFingerprintStatusUseCase: GetFingerprintStatusUseCase,
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
                        val password = getMasterPasswordUseCase.execute()
                        println("Key password is $password")
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
        runCatching { isPasswordExistUseCase.execute() }
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
            getFingerprintStatusUseCase.execute()
        }.onSuccess { isEnabled ->
            if (isEnabled) {
                next.invoke(MasterPasswordWish.GetFingerprint(isEnabled))
            }
        }
    }
}
