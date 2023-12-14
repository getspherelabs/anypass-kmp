package io.spherelabs.newtokendi

import io.spherelabs.newtokenapi.domain.usecase.InsertOtpWithCountUseCase
import io.spherelabs.newtokenimpl.domain.DefaultInsertOtpWithCount
import org.koin.dsl.module


val newTokenDomainModule = module {
    single<InsertOtpWithCountUseCase> { DefaultInsertOtpWithCount(get()) }
}
