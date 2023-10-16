package io.spherelabs.addnewpassworddomain.di

import io.spherelabs.addnewpassworddomain.usecase.AddNewPasswordUseCase
import io.spherelabs.addnewpassworddomain.usecase.DefaultAddNewPasswordUseCaseUseCase
import io.spherelabs.addnewpassworddomain.usecase.DefaultGetCategoriesUseCaseUseCase
import io.spherelabs.addnewpassworddomain.usecase.GetCategoriesUseCase
import org.koin.dsl.module

val addNewPasswordUseCaseDomainModule = module {
  single<AddNewPasswordUseCase> { DefaultAddNewPasswordUseCaseUseCase(get()) }
  single<GetCategoriesUseCase> { DefaultGetCategoriesUseCaseUseCase(get()) }
}
