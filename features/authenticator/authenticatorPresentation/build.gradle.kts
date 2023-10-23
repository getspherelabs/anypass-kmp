@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(projects.features.authenticator.authenticatorDomain) }
        }
    }
}
android {
    namespace = "io.spherelabs.authenticatorpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}