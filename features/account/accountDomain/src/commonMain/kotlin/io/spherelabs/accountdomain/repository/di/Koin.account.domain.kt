package io.spherelabs.accountdomain.repository.di

import io.spherelabs.accountdomain.repository.*
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val accountDomainModule = module {
    includes(platformModule())
    single<GetSizeOfStrongPassword> { DefaultGetSizeOfStrongPassword(get()) }
    single<GetSizeOfWeakPassword> { DefaultGetSizeOfWeakPassword(get()) }
    single<GetTotalPassword> { DefaultGetTotalPassword(get()) }
    single<OpenUrl> { DefaultOpenUrl(get()) }
    single<SetFingerPrint> { DefaultSetFingerPrint(get()) }
    single<GetFingerPrint> { DefaultGetFingerPrint(get()) }
    single<GetUser> { DefaultGetUser(get()) }
}
