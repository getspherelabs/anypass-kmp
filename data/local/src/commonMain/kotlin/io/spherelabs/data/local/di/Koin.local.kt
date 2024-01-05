package io.spherelabs.data.local.di


import domain.repository.PasswordHealthRepository
import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.addnewpasswordapi.domain.repository.AddNewPasswordRepository
import io.spherelabs.authenticatorapi.domain.repository.AuthenticatorRepository
import io.spherelabs.data.local.db.*
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultCounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.repository.*
import io.spherelabs.homeapi.repository.HomeRepository
import io.spherelabs.newtokenapi.domain.repository.NewTokenRepository
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
    single<PasswordHistoryDao> { DefaultPasswordHistoryDao(get()) }
    single<AddNewPasswordRepository> { DefaultAddNewPasswordRepository(get(), get()) }
    single<HomeRepository> { DefaultHomeRepository(get(), get()) }
    single<AccountRepository> { DefaultAccountRepository(get(), get()) }
    single<AuthenticatorRepository> { DefaultAuthenticatorRepository(get(), get()) }
    single<NewTokenRepository> { DefaultNewTokenRepository(get(), get()) }
    single<PasswordHealthRepository> { DefaultPasswordHealthRepository(get()) }
}
