plugins { id("anypass.multiplatform.domain") }

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":data:authManager"))
        implementation(project(":data:local"))
        implementation(project(":core:common"))
      }
    }
  }
}

android {
  namespace = "io.spherelabs.authdomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
