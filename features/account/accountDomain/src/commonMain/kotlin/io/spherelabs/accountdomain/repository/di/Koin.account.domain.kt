package io.spherelabs.accountdomain.repository.di


import io.spherelabs.accountdomain.repository.DefaultGetSizeOfStrongPassword
import io.spherelabs.accountdomain.repository.DefaultGetSizeOfWeakPassword
import io.spherelabs.accountdomain.repository.GetSizeOfStrongPassword
import io.spherelabs.accountdomain.repository.GetSizeOfWeakPassword
import org.koin.dsl.module

val accountDomainModule = module {
    single<GetSizeOfStrongPassword> { DefaultGetSizeOfStrongPassword(get()) }
    single<GetSizeOfWeakPassword> { DefaultGetSizeOfWeakPassword(get()) }
}
