@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.designsystem) }

kotlin {
  sourceSets {
    val commonMain by getting { dependencies { implementation(compose.ui) } }
  }
}

android {
  namespace = "io.spherelabs.foundation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
