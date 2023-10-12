@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // implementation(projects.features.c) }
            }
        }
    }
}

android {
    namespace = "io.spherelabs.changepasswordpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
