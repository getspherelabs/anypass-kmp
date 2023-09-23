package io.spherelabs.anypass.android

import android.app.Application
import io.spherelabs.anypass.di.initKoin
import io.spherelabs.anypass.sentry.initializeSentry
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

