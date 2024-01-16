package io.getspherelabs.convention.multiplatform

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureDomainMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api(findLibrary(name = "coroutine"))
                    api(findLibrary(name = "koin-core"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                    implementation(findLibrary(name = "coroutine-test"))
                    implementation(findLibrary(name = "turbine"))
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation(findLibrary(name = "koin-android"))
                }
            }
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
            }
        }
    }
}


internal fun Project.configureDesignSystemMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(findLibrary(name = "atomicfu"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependsOn(commonMain)
                dependencies {
                    api(findLibrary(name = "androidx-compose-graphics"))
                    api(findLibrary(name = "androidx-compose-ui"))
                    api(findLibrary(name = "androidx-compose-ui-text"))

                    implementation(findLibrary(name = "androidx-savedstate-ktx"))
                    implementation(findLibrary(name = "androidx-lifecycle-runtime"))
                    implementation(findLibrary(name = "androidx-lifecycle-viewmodel"))
                    implementation(findLibrary(name = "androidx-compose-animation"))
                    implementation(findLibrary(name = "androidx-compose-material-icons"))
                    implementation(findLibrary(name = "androidx-compose-material-ripple"))
                    implementation(findLibrary(name = "androidx-compose-runtime"))
                    implementation(findLibrary(name = "androidx-compose-ui-util"))
                    implementation(findLibrary(name = "androidx-compose-activity"))
                    implementation(findLibrary(name = "androidx-compose-ui-tooling-preview"))
                    implementation(findLibrary(name = "androidx-compose-ui-tooling"))
                    implementation(findLibrary(name = "androidx-lifecycle-compose"))
                    implementation(findLibrary(name = "androidx-lifecycle-java"))
                }
            }
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

internal fun Project.configurePresentationMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api(findLibrary(name = "coroutine"))
                    api(findLibrary(name = "koin-core"))
                    api(findLibrary(name = "meteor"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation(findLibrary(name = "androidx-lifecycle-viewmodel-ktx"))
                }
            }
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}


internal fun Project.configureCommonMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api(findLibrary(name = "coroutine"))
                    api(findLibrary(name = "datetime"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                    implementation(findLibrary(name = "assertk"))
                }
            }
            val androidMain by getting
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

internal fun Project.configurePrefsMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            all {
                languageSettings.optIn("com.russhwolf.settings.ExperimentalSettingsApi")
            }
            val commonMain by getting {
                dependencies {
                    api(findLibrary(name = "coroutine"))
                    api(findLibrary(name = "koin-core"))
                    api(findLibrary(name = "settings"))
                    api(findLibrary(name = "settings-coroutine"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation(findLibrary(name = "koin-android"))
                    implementation(findLibrary(name = "androidx-datastore"))
                    implementation(findLibrary(name = "androidx-datastore-pref"))
                    api(findLibrary(name = "settings-datastore"))
                }
            }
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

internal fun Project.configureValidationMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api(findLibrary(name = "coroutine"))
                    api(findLibrary(name = "koin-core"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                    implementation(findLibrary(name = "coroutine-test"))
                }
            }
            val androidMain by getting
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}


internal fun Project.configureResourceMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(findLibrary(name = "atomicfu"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependsOn(commonMain)
            }
            val androidUnitTest by getting
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

internal fun KotlinMultiplatformExtension.sourceSets(
    configure: Action<NamedDomainObjectContainer<KotlinSourceSet>>,
) {
    (this as ExtensionAware).extensions.configure("sourceSets", configure)
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val relativePath = projectDir.relativeTo(rootDir)
    val buildDir = layout.buildDirectory.get().asFile
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = buildDir.resolve("compose-metrics").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = buildDir.resolve("compose-reports").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
