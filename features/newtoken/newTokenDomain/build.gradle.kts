@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.common)
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
    namespace = "io.spherelabs.newtokendomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
