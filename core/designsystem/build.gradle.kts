plugins { id("anypass.multiplatform.designsystem") }

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material)
        implementation(compose.materialIconsExtended)
        implementation(compose.ui)
      }
    }
  }
}

android {
  namespace = "io.spherelabs.designsystem"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
