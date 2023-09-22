buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

plugins {
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
    id("org.jetbrains.compose").version("1.5.0").apply(false)
    id("org.jetbrains.kotlin.jvm").version("1.9.0").apply(false)
    id("dev.icerock.moko.kswift") version "0.6.1"
    id("com.google.gms.google-services") version "4.3.15" apply false

}
