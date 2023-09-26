plugins {
    id("anypass.multiplatform.validation")
}

android {
    namespace = "io.spherelabs.validation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
