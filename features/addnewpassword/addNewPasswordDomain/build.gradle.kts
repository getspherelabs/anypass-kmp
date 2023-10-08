@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}

android {
  namespace = "io.spherelabs.addnewpassworddomain"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
