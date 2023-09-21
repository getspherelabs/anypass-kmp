package io.spherelabs.firebase.di

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import io.spherelabs.firebase.FirebaseAuthManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single { FirebaseApp.initializeApp(androidContext()) }
    single { FirebaseAuth.getInstance() }
    single { FirebaseAuthManager(get()) }
}