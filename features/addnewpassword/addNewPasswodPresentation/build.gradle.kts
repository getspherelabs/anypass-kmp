plugins {
    id("anypass.multiplatform.presentation")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":features:addnewpassword:addNewPasswordDomain"))
                implementation(project(":core:common"))
            }
        }
    }
}

android {
    namespace = "io.spherelabs.addnewpasswodpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
