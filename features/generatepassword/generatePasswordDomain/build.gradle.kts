plugins {
    id("anypass.multiplatform.domain")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":manager:password"))
            }
        }
    }
}

android {
    namespace = "io.spherelabs.generatepassworddomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
