package io.spherelabs.help

import io.spherelabs.domain.usecase.GetFAQsUseCase
import io.spherelabs.help.domain.DefaultGetFAQsUseCase
import org.koin.dsl.module

val helpDomainModule = module {
    single<GetFAQsUseCase> { DefaultGetFAQsUseCase(get()) }
}
