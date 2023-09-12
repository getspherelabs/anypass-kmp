package io.spherelabs.addnewpassworddomain.di

import io.spherelabs.addnewpassworddomain.usecase.AddNewPassword
import io.spherelabs.addnewpassworddomain.usecase.DefaultAddNewPassword
import org.koin.dsl.module

val addNewPasswordDomainModule = module {
    single<AddNewPassword> { DefaultAddNewPassword(get()) }
}