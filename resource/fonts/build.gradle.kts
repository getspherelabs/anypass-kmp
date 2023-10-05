plugins { id("anypass.multiplatform.resource") }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
            }
        }
        val iosMain by getting {
            dependencies {
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.resource.fonts"
    compileSdk = 33

    sourceSets["main"].apply {
        res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    }
}
