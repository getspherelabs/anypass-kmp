package io.spherelabs.masterpassworddomain.di

import io.spherelabs.masterpassworddomain.*
import org.koin.dsl.module

val masterPasswordDomainModule = module {
    single<SetMasterPassword> { DefaultSetMasterPassword(get()) }
    single<IsPasswordExist> { DefaultIsPasswordExist(get()) }
    single<GetMasterPassword> { DefaultGetMasterPassword(get()) }
    single<GetFingerprintStatus> { DefaultGetFingerprintStatus(get()) }
}
