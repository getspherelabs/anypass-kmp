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
        api(projects.resource.images)
        implementation(projects.core.common)
        implementation(projects.features.newtoken.newtokenApi)
        implementation(libs.datetime)
        implementation(libs.voyager)
        implementation(compose.ui)
        implementation(compose.material)
        implementation(compose.material3)
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.materialIconsExtended)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)
        implementation(projects.resource.fonts)
        implementation(projects.resource.icons)
        implementation(projects.core.system.foundation)
      }
    }
  }
}

android {
  namespace = "io.spherelabs.newtokenimpl"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
