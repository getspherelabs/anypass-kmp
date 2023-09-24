import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.14.0")
    }
}

plugins {
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
    id("org.jetbrains.compose").version("1.5.0").apply(false)
    id("org.jetbrains.kotlin.jvm").version("1.9.0").apply(false)
    id("dev.icerock.moko.kswift").version("0.6.1")
    id("com.google.gms.google-services").version("4.3.15").apply(false)
    id("com.diffplug.spotless").version("6.17.0")
    id("org.jlleitschuh.gradle.ktlint").version(Version.ktlint)
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.diffplug.spotless")

    // It throws :shared:iosArm64CInteropApiElements

//    configure<SpotlessExtension> {
//        kotlin {
//            target("**/*.kt")
//            targetExclude("**/build/**/*.kt")
//            ktlint().setEditorConfigPath(
//                "${project.rootDir}/.editorconfig"
//            )
//            trimTrailingWhitespace()
//            endWithNewline()
//        }
//        kotlinGradle {
//            target("*.gradle.kts")
//            ktlint().setEditorConfigPath(
//                "${project.rootDir}/.editorconfig"
//            )
//            trimTrailingWhitespace()
//            endWithNewline()
//        }
//        format("xml") {
//            target("**/*.xml")
//        }
//    }

    ktlint {
        debug.set(false)
        verbose.set(true)
        version.set("0.37.2")
        enableExperimentalRules.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }
}
