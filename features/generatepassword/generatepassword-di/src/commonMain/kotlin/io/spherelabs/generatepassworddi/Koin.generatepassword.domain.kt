package io.spherelabs.generatepassworddi

import io.spherelabs.generatepasswordapi.domain.usecase.GeneratePasswordUseCase
import io.spherelabs.generatepasswordimpl.domain.usecase.DefaultGeneratePasswordUseCaseUseCase
import org.koin.dsl.module

val generatePasswordUseCaseDomainModule = module {
  single<GeneratePasswordUseCase> { DefaultGeneratePasswordUseCaseUseCase(get()) }
}
