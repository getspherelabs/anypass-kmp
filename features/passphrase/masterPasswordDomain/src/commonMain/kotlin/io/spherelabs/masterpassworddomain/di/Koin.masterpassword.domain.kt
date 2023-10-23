package io.spherelabs.masterpassworddomain.di

import io.spherelabs.masterpassworddomain.usecase.*
import org.koin.dsl.module

val masterPasswordDomainModule = module {
    single<SetMasterPasswordUseCase> { DefaultSetMasterPasswordUseCase(get()) }
    single<IsPasswordExistUseCase> { DefaultIsPasswordExistUseCase(get()) }
    single<GetMasterPasswordUseCase> { DefaultGetMasterPasswordUseCase(get()) }
    single<GetFingerprintStatusUseCase> { DefaultGetFingerprintStatusUseCase(get()) }
}
