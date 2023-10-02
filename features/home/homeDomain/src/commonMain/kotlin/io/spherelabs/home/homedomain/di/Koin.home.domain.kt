package io.spherelabs.home.homedomain.di

import io.spherelabs.home.homedomain.DefaultGetCategories
import io.spherelabs.home.homedomain.DefaultGetPasswordByCategory
import io.spherelabs.home.homedomain.GetCategories
import io.spherelabs.home.homedomain.GetPasswordsByCategory
import org.koin.dsl.module

val homeDomainModule = module {
    single<GetCategories> { DefaultGetCategories(get()) }
    single<GetPasswordsByCategory> { DefaultGetPasswordByCategory(get()) }
}
