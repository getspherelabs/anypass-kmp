package io.getspherelabs.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension

class ComposeMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.compose")
        }
        // configureCompose()
    }

}

fun Project.configureCompose() {
    with(extensions.getByType<ComposeExtension>()) {

    }
}


