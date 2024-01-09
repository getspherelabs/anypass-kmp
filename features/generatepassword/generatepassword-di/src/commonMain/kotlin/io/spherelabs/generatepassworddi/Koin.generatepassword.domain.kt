package io.spherelabs.generatepassworddi

import io.spherelabs.generatepasswordapi.domain.usecase.GeneratePasswordUseCase
import io.spherelabs.generatepasswordapi.domain.usecase.InsertPasswordHistoryUseCase
import io.spherelabs.generatepasswordimpl.domain.usecase.DefaultGeneratePasswordUseCaseUseCase
import io.spherelabs.generatepasswordimpl.domain.usecase.DefaultInsertPasswordHistoryUseCase
import org.koin.dsl.module

val generatePasswordUseCaseDomainModule = module {
    single<GeneratePasswordUseCase> { DefaultGeneratePasswordUseCaseUseCase(get()) }
    single<InsertPasswordHistoryUseCase> { DefaultInsertPasswordHistoryUseCase(get()) }
}
