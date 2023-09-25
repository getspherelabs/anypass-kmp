plugins { id("anypass.multiplatform.presentation") }

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":features:home:homeDomain"))
        implementation(project(":core:common"))
      }
    }
  }
}

android {
  namespace = "io.spherelabs.home.homepresentation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
