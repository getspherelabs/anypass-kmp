package io.spherelabs.accountdomain.repository.di

import io.spherelabs.accountdomain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val accountDomainModule = module {
    includes(platformModule())
    single<GetSizeOfStrongPasswordUseCase> { DefaultGetSizeOfStrongPasswordUseCase(get()) }
    single<GetSizeOfWeakPasswordUseCase> { DefaultGetSizeOfWeakPasswordUseCase(get()) }
    single<GetTotalPasswordUseCase> { DefaultGetTotalPasswordUseCase(get()) }
    single<OpenUrlUseCase> { DefaultOpenUrlUseCase(get()) }
    single<SetFingerPrintUseCase> { DefaultSetFingerPrintUseCase(get()) }
    single<GetFingerPrintUseCase> { DefaultGetFingerPrintUseCase(get()) }
    single<GetUserUseCase> { DefaultGetUserUseCase(get()) }
}
