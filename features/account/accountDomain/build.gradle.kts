plugins {
    id("anypass.multiplatform.domain")
}

android {
    namespace = "io.spherelabs.accountdomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
