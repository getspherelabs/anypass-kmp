package io.spherelabs.home.homedomain.di

import io.spherelabs.home.homedomain.DefaultGetCategories
import io.spherelabs.home.homedomain.GetCategories
import org.koin.dsl.module

val homeDomainModule = module { single<GetCategories> { DefaultGetCategories(get()) } }
