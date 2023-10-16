@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}
kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
          implementation(projects.data.local)
          implementation(projects.data.authManager)
          implementation(projects.data.prefs)
          implementation(projects.core.common)

      }
    }
  }
}

kotlin{
    sourceSets{
        val androidUnitTest by getting{
            dependencies{implementation(libs.konsist)}
        }
    }
}
android {
  namespace = "io.spherelabs.authdomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
