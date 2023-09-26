package io.spherelabs.addnewpasswodpresentation.di

import io.spherelabs.addnewpasswodpresentation.AddNewPasswordMiddleware
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordReducer
import org.koin.dsl.module

val addNewPasswordFeatureModule = module {
  single { AddNewPasswordMiddleware(get(), get()) }
  single { AddNewPasswordReducer() }
}
