plugins { id("anypass.multiplatform.presentation") }

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":features:account:accountDomain"))
        implementation(project(":core:common"))
      }
    }
  }
}

android {
  namespace = "io.spherelabs.accountpresentation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
