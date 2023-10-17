package io.spherelabs.authdomain.di

import io.spherelabs.authdomain.usecase.*
import org.koin.dsl.module

val authDomainModule = module {
    single<CreateEmailAndPassword> { DefaultCreateEmailAndPasswordUseCase(get(), get(), get()) }
    single<SignInWithEmailAndPassword> { DefaultSignInWithEmailAndPasswordUseCase(get()) }
    single<HasCurrentUserExist> { DefaultHasCurrentUserExistUseCase(get()) }
}
