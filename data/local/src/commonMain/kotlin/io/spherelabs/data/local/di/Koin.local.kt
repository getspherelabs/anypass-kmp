package io.spherelabs.data.local.di

import io.spherelabs.accountdomain.repository.AccountRepository
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import io.spherelabs.data.local.db.*
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultCounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.repository.DefaultAccountRepository
import io.spherelabs.data.local.repository.DefaultAddNewPasswordRepository
import io.spherelabs.data.local.repository.DefaultHomeRepository
import io.spherelabs.home.homedomain.repository.HomeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val localModule = module {
    includes(platformModule())
    factory { createDatabase(get()) }
    single<PasswordDao> { DefaultPasswordDao(get()) }
    single<CategoryDao> { DefaultCategoryDao(get()) }
    single<UserDao> { DefaultUserDao(get()) }
    single<OtpDao> { DefaultOtpDao(get()) }
    single<CounterDao> { DefaultCounterDao(get()) }
    single<AddNewPasswordRepository> { DefaultAddNewPasswordRepository(get(), get()) }
    single<HomeRepository> { DefaultHomeRepository(get(), get()) }
    single<AccountRepository> { DefaultAccountRepository(get(), get()) }
}
