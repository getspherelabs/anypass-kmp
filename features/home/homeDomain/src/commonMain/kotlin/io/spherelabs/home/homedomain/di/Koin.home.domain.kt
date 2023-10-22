package io.spherelabs.home.homedomain.di

import io.spherelabs.home.homedomain.usecase.DefaultGetCategoriesUseCase
import io.spherelabs.home.homedomain.usecase.DefaultGetPasswordByCategoryUseCase
import io.spherelabs.home.homedomain.usecase.GetCategoriesUseCase
import io.spherelabs.home.homedomain.usecase.GetPasswordsByCategoryUseCase
import org.koin.dsl.module

val homeDomainModule = module {
  single<GetCategoriesUseCase> { DefaultGetCategoriesUseCase(get()) }
  single<GetPasswordsByCategoryUseCase> { DefaultGetPasswordByCategoryUseCase(get()) }
}
