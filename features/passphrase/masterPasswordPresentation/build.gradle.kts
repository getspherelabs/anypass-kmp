plugins { id("anypass.multiplatform.presentation") }

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies { implementation(project(":features:passphrase:masterPasswordDomain")) }
    }
  }
}

android {
  namespace = "io.spherelabs.masterpasswordpresentation"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
