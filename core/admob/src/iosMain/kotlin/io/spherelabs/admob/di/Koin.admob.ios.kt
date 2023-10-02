package io.spherelabs.admob.di

import cocoapods.GoogleMobileAds.GADMobileAds
import org.koin.dsl.module

actual fun platformModule() = module {
    single { GADMobileAds.sharedInstance().startWithCompletionHandler {  } }
}
