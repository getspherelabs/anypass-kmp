plugins {
    id("anypass.multiplatform.presentation")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":features:generatepassword:generatePasswordDomain"))
            }
        }
    }
}

android {
    namespace = "io.spherelabs.generatepasswordpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
