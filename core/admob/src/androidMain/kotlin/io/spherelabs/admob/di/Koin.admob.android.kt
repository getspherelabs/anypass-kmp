package io.spherelabs.admob.di

import android.annotation.SuppressLint
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


@SuppressLint("MissingPermission")
actual fun platformModule() = module {
    single { MobileAds.initialize(androidContext()) }
    single {
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("33BE2250B43518CCDA7DE426D04EE231")).build(),
        )
    }
}
