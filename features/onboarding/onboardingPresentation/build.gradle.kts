plugins {
  id("anypass.multiplatform.presentation")
  id("anypass.compose")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
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
