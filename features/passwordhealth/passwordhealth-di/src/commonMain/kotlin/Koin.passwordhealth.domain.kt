package io.spherelabs.passwordhealthdi

import domain.usecase.GetPasswordHealthUseCase
import domain.usecase.GetPasswordProgressUseCase
import domain.usecase.GetPasswordStatsUseCase
import org.koin.dsl.module
import passwordhealthimpl.domain.DefaultGetPasswordHealthUseCase
import passwordhealthimpl.domain.DefaultGetPasswordProgressUseCase
import passwordhealthimpl.domain.DefaultGetPasswordStatsUseCase

val passwordHealthDomainModule = module {
    single<GetPasswordHealthUseCase> {
        DefaultGetPasswordHealthUseCase(get())
    }
    single<GetPasswordStatsUseCase> {
        DefaultGetPasswordStatsUseCase(get())
    }
    single<GetPasswordProgressUseCase> {
        DefaultGetPasswordProgressUseCase(get())
    }
}
