package io.spherelabs.newtokendomain.di

import io.spherelabs.newtokendomain.usecase.DefaultInsertOtpWithCount
import io.spherelabs.newtokendomain.usecase.InsertOtpWithCountUseCase
import org.koin.dsl.module

val newTokenDomainModule = module {
    single<InsertOtpWithCountUseCase> { DefaultInsertOtpWithCount(get()) }
}
