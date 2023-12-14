package io.spherelabs.authenticatordi

import io.spherelabs.authenticatorapi.domain.usecase.GetRealTimeOneTimePassword
import io.spherelabs.authenticatorapi.domain.usecase.IncrementCounterUseCase
import io.spherelabs.authenticatorimpl.domain.usecase.DefaultGetRealTimeOneTimePassword
import io.spherelabs.authenticatorimpl.domain.usecase.DefaultIncrementCounterUseCase
import org.koin.dsl.module

val authenticatorDomainModule = module {
    single<GetRealTimeOneTimePassword>
    { DefaultGetRealTimeOneTimePassword(get(), get()) }
    single<IncrementCounterUseCase> { DefaultIncrementCounterUseCase(get()) }
}
