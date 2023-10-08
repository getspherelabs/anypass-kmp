@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
          implementation(projects.features.onboarding.onboardingDomain)
        implementation(project(":features:onboarding:onboardingDomain"))
        implementation(compose.runtime)
      }
    }
  }
}

android {
  namespace = "io.spherelabs.features.onboardingpresentation"
  compileSdk = 33

  defaultConfig { minSdk = 24 }
}
