package io.spherelabs.anypass.android.initializer

import android.content.Context
import androidx.startup.Initializer
import io.spherelabs.anypass.sentry.initializeSentry

class SentryInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        println("Initializer: Sentry is initialized.")
        initializeSentry(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}
