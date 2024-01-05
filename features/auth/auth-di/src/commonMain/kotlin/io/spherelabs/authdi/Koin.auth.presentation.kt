package io.spherelabs.authdi

import io.spherelabs.authimpl.presentation.signin.SignInMiddleware
import io.spherelabs.authimpl.presentation.signin.SignInReducer
import io.spherelabs.authimpl.presentation.signin.SignInValidateMiddleware
import io.spherelabs.authimpl.presentation.signup.SignUpMiddleware
import io.spherelabs.authimpl.presentation.signup.SignUpReducer
import io.spherelabs.authimpl.presentation.signup.SignUpValidateMiddleware
import org.koin.dsl.module

val authFeatureModule = module {
    single { SignUpReducer() }
    single { SignUpMiddleware(get()) }
    single { SignUpValidateMiddleware(get(), get(), get(), get()) }
    single { SignInReducer() }
    single { SignInMiddleware(get(), get(),get(), get()) }
    single { SignInValidateMiddleware(get(), get()) }
}
