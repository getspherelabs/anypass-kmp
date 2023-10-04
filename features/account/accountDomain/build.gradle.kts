plugins { id("anypass.multiplatform.domain") }
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:designsystem"))
                implementation(project(":data:prefs"))
            }
        }
    }
}

android {
  namespace = "io.spherelabs.accountdomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
