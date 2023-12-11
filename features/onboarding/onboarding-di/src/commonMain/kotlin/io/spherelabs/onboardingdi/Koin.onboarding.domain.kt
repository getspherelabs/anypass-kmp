package io.spherelabs.onboardingdi

import io.spherelabs.onboardingapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.onboardingapi.domain.usecase.IsFirstTimeUseCase
import io.spherelabs.onboardingapi.domain.usecase.SetIsFirstTimeUseCase
import io.spherelabs.onboardingimpl.domain.DefaultHasCurrentUserExistUseCase
import io.spherelabs.onboardingimpl.domain.DefaultIsFirstTimeUseCase
import io.spherelabs.onboardingimpl.domain.DefaultSetIsFirstTimeUseCase
import org.koin.dsl.module


val onboardingDomainModule = module {
    single<SetIsFirstTimeUseCase> { DefaultSetIsFirstTimeUseCase(get()) }
    single<IsFirstTimeUseCase> { DefaultIsFirstTimeUseCase(get()) }
    single<HasCurrentUserExist> { DefaultHasCurrentUserExistUseCase(get()) }
}
