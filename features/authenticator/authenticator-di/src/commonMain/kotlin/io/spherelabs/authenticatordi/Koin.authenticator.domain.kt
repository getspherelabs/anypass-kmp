package io.spherelabs.authenticatordi

import io.spherelabs.authenticatorapi.domain.usecase.GetOtpUseCase
import io.spherelabs.authenticatorapi.domain.usecase.GetRealTimeOneTimePassword
import io.spherelabs.authenticatorapi.domain.usecase.IncrementCounterUseCase
import io.spherelabs.authenticatorimpl.domain.usecase.DefaultGetOtpUseCase
import io.spherelabs.authenticatorimpl.domain.usecase.DefaultGetRealTimeOneTimePassword
import io.spherelabs.authenticatorimpl.domain.usecase.DefaultIncrementCounterUseCase
import io.spherelabs.otp.DefaultOtpManager
import io.spherelabs.otp.OtpManager
import org.koin.dsl.module

val authenticatorDomainModule = module {
    single<OtpManager> {
        DefaultOtpManager()
    }

    factory<GetRealTimeOneTimePassword>
    { DefaultGetRealTimeOneTimePassword(get(), get()) }
    single<IncrementCounterUseCase> { DefaultIncrementCounterUseCase(get()) }
    factory<GetOtpUseCase> {
        DefaultGetOtpUseCase(get())
    }
}
