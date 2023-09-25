plugins {
    id("anypass.multiplatform.presentation")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":features:auth:authDomain"))
            }
        }
    }
}

android {
    namespace = "io.spherelabs.authpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
