plugins { id("anypass.multiplatform.prefs") }

android {
  namespace = "io.spherelabs.data.settings"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
