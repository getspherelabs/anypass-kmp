package io.spherelabs.addnewpassworddi

import io.spherelabs.addnewpasswordapi.domain.usecase.AddNewPasswordUseCase
import io.spherelabs.addnewpasswordapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.addnewpasswordimpl.domain.DefaultAddNewPasswordUseCaseUseCase
import io.spherelabs.addnewpasswordimpl.domain.DefaultGetCategoriesUseCaseUseCase
import org.koin.dsl.module

val addNewPasswordUseCaseDomainModule = module {
  single<AddNewPasswordUseCase> { DefaultAddNewPasswordUseCaseUseCase(get()) }
  single<GetCategoriesUseCase> { DefaultGetCategoriesUseCaseUseCase(get()) }
}
