@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
}
kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
          implementation(projects.features.auth.authDomain)
          implementation(projects.core.validation)
      }

    }
  }
}

android {
  namespace = "io.spherelabs.authpresentation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
