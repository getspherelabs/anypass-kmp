package io.spherelabs.firebase.di


import cocoapods.FirebaseAuth.FIRAuth
import io.spherelabs.firebase.FirebaseAuthManager
import org.koin.dsl.module
import cocoapods.FirebaseCore.FIRApp

actual fun platformModule() = module {
    single { FIRApp.configure() }
    single { FirebaseAuthManager(FIRAuth.auth()) }
}
