package io.spherelabs.authdomain.di

import io.spherelabs.authdomain.*
import org.koin.dsl.module

val authDomainModule = module {
    single<CreateEmailAndPassword> { DefaultCreateEmailAndPassword(get(), get(), get()) }
    single<SignInWithEmailAndPassword> { DefaultSignInWithEmailAndPassword(get()) }
    single<HasCurrentUserExist> { DefaultHasCurrentUserExist(get()) }
}
