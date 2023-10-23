package io.spherelabs.onboardingdomain.di

import io.spherelabs.onboardingdomain.usecase.DefaultIsFirstTimeUseCase
import io.spherelabs.onboardingdomain.usecase.DefaultSetIsFirstTimeUseCase
import io.spherelabs.onboardingdomain.usecase.IsFirstTimeUseCase
import io.spherelabs.onboardingdomain.usecase.SetIsFirstTimeUseCase
import org.koin.dsl.module

val onboardingDomainModule = module {
  single<SetIsFirstTimeUseCase> { DefaultSetIsFirstTimeUseCase(get()) }
  single<IsFirstTimeUseCase> { DefaultIsFirstTimeUseCase(get()) }
}
