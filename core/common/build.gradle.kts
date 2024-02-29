
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.common) }

android {
  namespace = "io.spherelabs.common"
  compileSdk = 33
  defaultConfig { minSdk = 24 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
