@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.designsystem)
                implementation(projects.data.prefs)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.accountdomain"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}
