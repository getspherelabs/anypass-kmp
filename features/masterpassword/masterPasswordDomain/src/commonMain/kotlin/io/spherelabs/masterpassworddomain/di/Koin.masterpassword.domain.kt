package io.spherelabs.masterpassworddomain.di

import io.spherelabs.masterpassworddomain.DefaultGetMasterPassword
import io.spherelabs.masterpassworddomain.DefaultIsPasswordExist
import io.spherelabs.masterpassworddomain.DefaultSetMasterPassword
import io.spherelabs.masterpassworddomain.GetMasterPassword
import io.spherelabs.masterpassworddomain.IsPasswordExist
import io.spherelabs.masterpassworddomain.SetMasterPassword
import org.koin.dsl.module

val masterPasswordDomainModule = module {
  single<SetMasterPassword> { DefaultSetMasterPassword(get()) }
  single<IsPasswordExist> { DefaultIsPasswordExist(get()) }
  single<GetMasterPassword> { DefaultGetMasterPassword(get()) }
}
