package io.spherelabs.validation.di

import io.spherelabs.validation.DefaultEmailValidation
import io.spherelabs.validation.DefaultKeyPasswordValidation
import io.spherelabs.validation.DefaultNameValidation
import io.spherelabs.validation.DefaultPasswordValidation
import io.spherelabs.validation.DefaultWebsiteValidation
import io.spherelabs.validation.EmailValidation
import io.spherelabs.validation.KeyPasswordValidation
import io.spherelabs.validation.NameValidation
import io.spherelabs.validation.PasswordValidation
import io.spherelabs.validation.WebsiteValidation
import org.koin.dsl.module

val validationModule = module {
    single<EmailValidation> { DefaultEmailValidation() }
    single<NameValidation> { DefaultNameValidation() }
    single<PasswordValidation> { DefaultPasswordValidation() }
    single<WebsiteValidation> { DefaultWebsiteValidation() }
    single<KeyPasswordValidation> { DefaultKeyPasswordValidation() }
}
