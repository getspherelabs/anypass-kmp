@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.common) }

android {
  namespace = "io.spherelabs.common"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
