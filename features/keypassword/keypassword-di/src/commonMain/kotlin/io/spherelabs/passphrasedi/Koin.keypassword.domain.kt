package io.spherelabs.passphrasedi

import io.spherelabs.passphraseapi.domain.usecase.GetFingerprintStatusUseCase
import io.spherelabs.passphraseapi.domain.usecase.GetKeyPasswordUseCase
import io.spherelabs.passphraseapi.domain.usecase.IsPasswordExistUseCase
import io.spherelabs.passphraseapi.domain.usecase.SetKeyPasswordUseCase
import io.spherelabs.passphraseimpl.domain.DefaultGetFingerprintStatusUseCase
import io.spherelabs.passphraseimpl.domain.DefaultGetKeyPasswordUseCase
import io.spherelabs.passphraseimpl.domain.DefaultIsKeyPasswordExistUseCase
import io.spherelabs.passphraseimpl.domain.DefaultSetKeyPasswordUseCase
import org.koin.dsl.module

val keyPasswordDomainModule = module {
    single<SetKeyPasswordUseCase> { DefaultSetKeyPasswordUseCase(get()) }
    single<IsPasswordExistUseCase> { DefaultIsKeyPasswordExistUseCase(get()) }
    single<GetKeyPasswordUseCase> { DefaultGetKeyPasswordUseCase(get()) }
    single<GetFingerprintStatusUseCase> { DefaultGetFingerprintStatusUseCase(get()) }
}
