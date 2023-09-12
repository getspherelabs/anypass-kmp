package io.spherelabs.lockerkmp.android

import android.app.Application
import io.spherelabs.lockerkmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class LockerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
           androidContext(this@LockerApplication)
        }
    }
}

