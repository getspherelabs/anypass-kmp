package io.getspherelabs.convention

import io.getspherelabs.convention.multiplatform.configurePrefsMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PrefsMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
        }
        configureSpotless()
        extensions.configure<KotlinMultiplatformExtension>() {
            configurePrefsMultiplatform(this)
        }
    }
}
