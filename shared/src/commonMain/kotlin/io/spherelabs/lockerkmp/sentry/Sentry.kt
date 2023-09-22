package io.spherelabs.lockerkmp.sentry

import io.sentry.kotlin.multiplatform.Context
import io.sentry.kotlin.multiplatform.OptionsConfiguration
import io.sentry.kotlin.multiplatform.Sentry

private val optionsConfiguration: OptionsConfiguration = {
    it.dsn =
        "https://3e1eb94d0acc29f3dc2902d63199db7c@o4505921322418176.ingest.sentry.io/4505921324056576"
    it.attachStackTrace = true
    it.attachThreads = true
    it.attachScreenshot = true
    it.beforeSend = { event ->
        if (event.environment == "test") {
            null
        } else {
            event
        }
    }
    it.beforeBreadcrumb = { breadcrumb ->
        breadcrumb.message = "Add message before every breadcrumb"
        breadcrumb
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
        it.setContext("Custom Context", "Shared Context")
        it.setTag("custom-tag", "from shared code")
    }
}