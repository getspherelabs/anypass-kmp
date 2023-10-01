package io.spherelabs.firebase.di


import cocoapods.FirebaseAuth.FIRAuth
import io.spherelabs.firebase.FirebaseAuthManager
import org.koin.dsl.module


actual fun platformModule() = module {

    single { FirebaseAuthManager(FIRAuth.auth()) }
}
