@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.features.newtoken.newTokenDomain)
                implementation(libs.datetime)
                implementation(compose.runtime)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.newtokenpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
