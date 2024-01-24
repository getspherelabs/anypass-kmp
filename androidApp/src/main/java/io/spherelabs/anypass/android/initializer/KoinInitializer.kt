package io.spherelabs.anypass.android.initializer

import android.content.Context
import androidx.startup.Initializer
import io.spherelabs.anypass.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication

class KoinInitializer : Initializer<KoinApplication> {

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    override fun create(context: Context): KoinApplication {
        return initKoin {
            println("Initializer: Koin is initialized.")
            androidContext(context)
        }
    }
}
