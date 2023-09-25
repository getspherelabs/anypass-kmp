package io.getspherelabs.convention

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {



    with(pluginManager) {
        apply("com.diffplug.spotless")
    }

    spotless {
        lineEndings = LineEnding.PLATFORM_NATIVE

        kotlin {
            target("src/**/*.kt")
            ktfmt("0.44").googleStyle()
        }

        kotlinGradle {
            target("*.kts")
            ktfmt("0.44").googleStyle()
        }
    }

}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
