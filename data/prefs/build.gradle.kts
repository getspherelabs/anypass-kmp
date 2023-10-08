@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.prefs) }

android {
  namespace = "io.spherelabs.data.settings"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
