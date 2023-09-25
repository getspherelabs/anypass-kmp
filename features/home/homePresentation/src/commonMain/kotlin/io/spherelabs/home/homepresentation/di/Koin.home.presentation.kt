package io.spherelabs.home.homepresentation.di

import io.spherelabs.home.homepresentation.HomeMiddleware
import io.spherelabs.home.homepresentation.HomeReducer
import org.koin.dsl.module

val homePresentationModule = module {
  single { HomeReducer() }
  single { HomeMiddleware(get()) }
}
