@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

kotlin {
    sourceSets {
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.konsist)
            }
        }
    }
}

android {
  namespace = "io.spherelabs.home.homedomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
