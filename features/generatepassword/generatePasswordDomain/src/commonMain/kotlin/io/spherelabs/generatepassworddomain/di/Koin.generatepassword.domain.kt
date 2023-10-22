package io.spherelabs.generatepassworddomain.di

import io.spherelabs.generatepassworddomain.usecase.DefaultGeneratePasswordUseCaseUseCase
import io.spherelabs.generatepassworddomain.usecase.GeneratePasswordUseCase
import org.koin.dsl.module

val generatePasswordUseCaseDomainModule = module {
  single<GeneratePasswordUseCase> { DefaultGeneratePasswordUseCaseUseCase(get()) }
}
