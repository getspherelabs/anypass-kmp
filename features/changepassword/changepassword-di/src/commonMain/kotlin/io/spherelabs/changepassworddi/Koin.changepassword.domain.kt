package io.spherelabs.changepassworddi

import io.spherelabs.changepasswordapi.domain.usecase.GetCurrentKeyPasswordUseCase
import io.spherelabs.changepasswordapi.domain.usecase.SetNewKeyPasswordUseCase
import io.spherelabs.changepasswordimpl.domain.usecase.DefaultGetCurrentKeyPasswordUseCase
import io.spherelabs.changepasswordimpl.domain.usecase.DefaultSetNewKeyPasswordUseCase
import org.koin.dsl.module

val changePasswordDomainModule = module {
    single<SetNewKeyPasswordUseCase> { DefaultSetNewKeyPasswordUseCase(get()) }
    single<GetCurrentKeyPasswordUseCase> { DefaultGetCurrentKeyPasswordUseCase(get()) }
}
