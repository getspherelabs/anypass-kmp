@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

kotlin {
    sourceSets {
        val commonMain by getting { dependencies {
            implementation(projects.data.prefs) } }
    }
}

android {
  namespace = "io.spherelabs.onboardingdomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
