package io.spherelabs.addnewpassworddomain.di

import io.spherelabs.addnewpassworddomain.usecase.AddNewPassword
import io.spherelabs.addnewpassworddomain.usecase.DefaultAddNewPassword
import io.spherelabs.addnewpassworddomain.usecase.DefaultGetCategories
import io.spherelabs.addnewpassworddomain.usecase.GetCategories
import org.koin.dsl.module

val addNewPasswordDomainModule = module {
  single<AddNewPassword> { DefaultAddNewPassword(get()) }
  single<GetCategories> { DefaultGetCategories(get()) }
}
