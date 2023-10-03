package io.spherelabs.accountdomain.repository.di

import io.spherelabs.accountdomain.repository.DefaultGetPasswordSize
import io.spherelabs.accountdomain.repository.GetPasswordSize
import org.koin.dsl.module

val accountDomainModule = module { single<GetPasswordSize> { DefaultGetPasswordSize(get()) } }
