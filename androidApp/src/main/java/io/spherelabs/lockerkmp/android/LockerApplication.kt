package io.spherelabs.lockerkmp.android

import android.app.Application
import io.spherelabs.lockerkmp.di.initKoin
import io.spherelabs.lockerkmp.sentry.initializeSentry
import org.koin.android.ext.koin.androidContext

class LockerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSentry(this)
        initKoin {
            androidContext(this@LockerApplication)
        }
    }
}

