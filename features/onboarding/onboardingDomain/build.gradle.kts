plugins { id("anypass.multiplatform.domain") }

kotlin {
  sourceSets {
    val commonMain by getting { dependencies { implementation(project(":data:prefs")) } }
  }
}

android {
  namespace = "io.spherelabs.onboardingdomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
