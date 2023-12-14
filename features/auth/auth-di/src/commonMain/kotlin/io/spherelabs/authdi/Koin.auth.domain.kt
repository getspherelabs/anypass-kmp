package io.spherelabs.authdi

import io.spherelabs.authapi.domain.usecase.CreateEmailAndPassword
import io.spherelabs.authapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.authapi.domain.usecase.SignInWithEmailAndPassword
import io.spherelabs.authimpl.domain.DefaultCreateEmailAndPasswordUseCase
import io.spherelabs.authimpl.domain.DefaultHasCurrentUserExistUseCase
import io.spherelabs.authimpl.domain.DefaultSignInWithEmailAndPasswordUseCase
import org.koin.dsl.module

val authDomainModule = module {
    single<CreateEmailAndPassword> { DefaultCreateEmailAndPasswordUseCase(get(), get(), get()) }
    single<SignInWithEmailAndPassword> { DefaultSignInWithEmailAndPasswordUseCase(get()) }
    single<HasCurrentUserExist> { DefaultHasCurrentUserExistUseCase(get()) }
}
