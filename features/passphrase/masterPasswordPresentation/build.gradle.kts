@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
}
kotlin {
    sourceSets {
        val commonMain by getting {

            dependencies {
                implementation(projects.features.passphrase.masterPasswordDomain)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.masterpasswordpresentation"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}
