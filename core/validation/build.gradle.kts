@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.validation)
}

android {
    namespace = "io.spherelabs.validation"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}
