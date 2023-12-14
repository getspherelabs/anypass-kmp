package io.spherelabs.generatepassworddi

import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordMiddleware
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordReducer
import org.koin.dsl.module

val generatePasswordPresentationModule = module {
  single { GeneratePasswordReducer() }
  single { GeneratePasswordMiddleware(get()) }
}
