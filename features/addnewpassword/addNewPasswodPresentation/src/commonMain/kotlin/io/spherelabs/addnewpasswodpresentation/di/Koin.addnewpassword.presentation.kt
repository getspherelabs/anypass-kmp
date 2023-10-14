package io.spherelabs.addnewpasswodpresentation.di

import io.spherelabs.addnewpasswodpresentation.AddNewPasswordMiddleware
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordReducer
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordValidateMiddleware
import org.koin.dsl.module

val addNewPasswordFeatureModule = module {
    single { AddNewPasswordMiddleware(get(), get()) }
    single { AddNewPasswordReducer() }
    single { AddNewPasswordValidateMiddleware(get(), get(), get(), get()) }
}
