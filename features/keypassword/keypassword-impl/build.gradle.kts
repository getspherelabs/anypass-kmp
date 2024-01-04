@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.anypass.presentation)
  alias(libs.plugins.anypass.compose)
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        api(projects.core.designsystem)
        api(projects.data.prefs)
        api(projects.manager.biometry)
        api(projects.features.navigation.navigationApi)

        implementation(projects.features.keypassword.keypasswordApi)
        implementation(libs.voyager)
        implementation(compose.ui)
        implementation(compose.material)
        implementation(compose.material3)
        implementation(compose.runtime)
        implementation(compose.foundation)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)
        implementation(projects.core.common)
        implementation(projects.resource.fonts)
        implementation(projects.resource.icons)
        implementation(projects.core.system.foundation)
      }
    }
    val androidUnitTest by getting { dependencies { implementation(libs.konsist) } }
  }
}

android {
  namespace = "io.spherelabs.passphraseimpl"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
