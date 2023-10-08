@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
}
kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
          implementation(projects.features.addnewpassword.addNewPasswordDomain)
          implementation(projects.core.common)
      }
    }
  }
}

android {
  namespace = "io.spherelabs.addnewpasswodpresentation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
