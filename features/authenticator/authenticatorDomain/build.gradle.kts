@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.common)
                implementation(projects.manager.otp)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.konsist)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.authdomain"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}

android {
    namespace = "io.spherelabs.authenticatordomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
