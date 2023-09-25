package io.spherelabs.masterpasswordpresentation.di

import io.spherelabs.masterpasswordpresentation.MasterPasswordMiddleware
import io.spherelabs.masterpasswordpresentation.MasterPasswordReducer
import org.koin.dsl.module

val masterPasswordFeatureModule = module {
  single { MasterPasswordMiddleware(get(), get(), get()) }
  single { MasterPasswordReducer() }
}
