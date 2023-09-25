package io.spherelabs.authdomain.di

import io.spherelabs.authdomain.*
import org.koin.dsl.module

val authDomainModule = module {
  single<CreateEmailAndPassword> { DefaultCreateEmailAndPassword(get(), get()) }
  single<SignInWithEmailAndPassword> { DefaultSignInWithEmailAndPassword(get()) }
  single<EmailValidation> { ValidateEmail() }
  single<NameValidation> { ValidateName() }
  single<PasswordValidation> { ValidatePassword() }
  single<HasCurrentUserExist> { DefaultHasCurrentUserExist(get()) }
}
