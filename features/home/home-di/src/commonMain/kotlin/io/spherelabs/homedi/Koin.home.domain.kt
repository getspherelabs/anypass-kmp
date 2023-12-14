package io.spherelabs.homedi

import io.spherelabs.homeapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.homeapi.domain.usecase.GetPasswordsByCategoryUseCase
import io.spherelabs.homeimpl.domain.DefaultGetCategoriesUseCase
import io.spherelabs.homeimpl.domain.DefaultGetPasswordByCategoryUseCase
import org.koin.dsl.module

val homeDomainModule = module {
    single<GetCategoriesUseCase> { DefaultGetCategoriesUseCase(get()) }
    single<GetPasswordsByCategoryUseCase> { DefaultGetPasswordByCategoryUseCase(get()) }
}
