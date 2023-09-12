package io.spherelabs.data.local.di

import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import io.spherelabs.data.local.db.DefaultPasswordDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.db.createDatabase
import io.spherelabs.data.local.repository.DefaultAddNewPasswordRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val localModule = module {
    includes(platformModule())
    factory { createDatabase(get()) }
    single<PasswordDao> { DefaultPasswordDao(get()) }
    single<AddNewPasswordRepository> { DefaultAddNewPasswordRepository(get()) }
}