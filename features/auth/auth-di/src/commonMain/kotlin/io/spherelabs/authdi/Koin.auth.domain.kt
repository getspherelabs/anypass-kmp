package io.spherelabs.authdi

import io.spherelabs.authapi.domain.usecase.*
import io.spherelabs.authimpl.domain.*
import org.koin.dsl.module

val authDomainModule = module {
    single<CreateEmailAndPassword> { DefaultCreateEmailAndPasswordUseCase(get(), get(), get()) }
    single<SignInWithEmailAndPassword> { DefaultSignInWithEmailAndPasswordUseCase(get()) }
    single<HasCurrentUserExist> { DefaultHasCurrentUserExistUseCase(get()) }
    single<SetKeyPasswordUseCase>() { DefaultSetKeyPasswordUseCase(get()) }
    single<InsertUserUseCase> { DefaultInsertUserUseCase(get()) }
}
