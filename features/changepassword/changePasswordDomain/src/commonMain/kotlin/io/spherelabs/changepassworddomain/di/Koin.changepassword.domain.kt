package io.spherelabs.changepassworddomain.di

import io.spherelabs.changepassworddomain.usecase.DefaultGetCurrentKeyPasswordUseCase
import io.spherelabs.changepassworddomain.usecase.DefaultSetNewKeyPasswordUseCase
import io.spherelabs.changepassworddomain.usecase.GetCurrentKeyPasswordUseCase
import io.spherelabs.changepassworddomain.usecase.SetNewKeyPasswordUseCase
import org.koin.dsl.module

val changePasswordDomainModule = module {
    single<SetNewKeyPasswordUseCase> { DefaultSetNewKeyPasswordUseCase(get()) }
    single<GetCurrentKeyPasswordUseCase> { DefaultGetCurrentKeyPasswordUseCase(get()) }
}
