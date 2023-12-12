@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.api)
}

android {
    namespace = "io.spherelabs.accountapi"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
