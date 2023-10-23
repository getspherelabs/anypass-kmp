package io.spherelabs.authenticatordomain.di

import io.spherelabs.authenticatordomain.usecase.*
import org.koin.dsl.module

val authenticatorDomainModule = module {
    single<GetRealTimeOneTimePassword>
    { DefaultGetRealTimeOneTimePassword(get(), get()) }
    single<IncrementCounterUseCase> { DefaultIncrementCounterUseCase(get()) }
}
