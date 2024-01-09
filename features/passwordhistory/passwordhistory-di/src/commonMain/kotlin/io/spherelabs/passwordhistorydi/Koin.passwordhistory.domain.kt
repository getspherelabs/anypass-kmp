package io.spherelabs.passwordhistorydi

import io.spherelabs.passwordhistoryapi.usecase.ClearAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryapi.usecase.GetAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryimpl.domain.DefaultClearPasswordHistoryUseCase
import io.spherelabs.passwordhistoryimpl.domain.DefaultGetAllPasswordHistoryUseCase
import org.koin.dsl.module

val passwordHistoryDomainModule = module {
    single<GetAllPasswordHistoryUseCase> {
        DefaultGetAllPasswordHistoryUseCase(get())
    }
    single<ClearAllPasswordHistoryUseCase> {
        DefaultClearPasswordHistoryUseCase(get())
    }
}
