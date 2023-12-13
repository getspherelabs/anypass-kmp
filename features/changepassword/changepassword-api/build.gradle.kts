@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.api)
}

android {
    namespace = "io.spherelabs.changepasswordapi"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
