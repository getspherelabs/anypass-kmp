package io.getspherelabs.convention

import io.getspherelabs.convention.multiplatform.configurePresentationMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PresentationMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("com.autonomousapps.dependency-analysis")
        }
        configureSpotless()
        extensions.configure<KotlinMultiplatformExtension>() {
            configurePresentationMultiplatform(this)
        }
    }
}
