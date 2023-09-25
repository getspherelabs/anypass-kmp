package io.spherelabs.authpresentation

import io.spherelabs.authpresentation.signin.SignInMiddleware
import io.spherelabs.authpresentation.signin.SignInReducer
import io.spherelabs.authpresentation.signin.SignInValidateMiddleware
import io.spherelabs.authpresentation.signup.SignUpMiddleware
import io.spherelabs.authpresentation.signup.SignUpReducer
import io.spherelabs.authpresentation.signup.ValidateMiddleware
import org.koin.dsl.module

val authFeatureModule = module {
  single { SignUpReducer() }
  single { SignUpMiddleware(get()) }
  single { ValidateMiddleware(get(), get(), get()) }
  single { SignInReducer() }
  single { SignInMiddleware(get(), get()) }
  single { SignInValidateMiddleware(get(), get()) }
}
