package io.spherelabs.anypass.sentry

import io.sentry.kotlin.multiplatform.Context
import io.sentry.kotlin.multiplatform.OptionsConfiguration
import io.sentry.kotlin.multiplatform.Sentry
import io.spherelabs.anypass.BuildKonfig

private val optionsConfiguration: OptionsConfiguration = { option ->
    option.dsn = BuildKonfig.SENTRY_DSN
    option.attachStackTrace = true
    option.attachThreads = true
    option.attachScreenshot = true
    option.beforeSend = { event ->
        if (event.environment == "test") {
            null
        } else {
            event
        }
    }
}

fun initializeSentry(context: Context?) {
    if (context != null) {
        Sentry.init(context = context, optionsConfiguration)
    } else {
        Sentry.init(optionsConfiguration)
    }
    configureSentryScope()
}

private fun configureSentryScope() {
    Sentry.configureScope {
        it.setContext("AnyPass", "Shared Context")
        it.setTag("anypass-tag", "from shared code")
    }
}
