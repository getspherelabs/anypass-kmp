package io.spherelabs.addnewpassworddi

import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordMiddleware
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordReducer
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordValidateMiddleware
import org.koin.dsl.module

val addNewPasswordFeatureModule = module {
    single { AddNewPasswordMiddleware(get(), get()) }
    single { AddNewPasswordReducer() }
    single { AddNewPasswordValidateMiddleware(get(), get(), get(), get()) }
}
