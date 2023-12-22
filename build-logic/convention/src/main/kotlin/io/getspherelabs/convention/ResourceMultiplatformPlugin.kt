package io.getspherelabs.convention

import io.getspherelabs.convention.multiplatform.configureResourceMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ResourceMultiplatformPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
        }
        configureCompose()
        // configureSpotless()
        extensions.configure<KotlinMultiplatformExtension>() {
            configureResourceMultiplatform(this)
        }
    }
}
