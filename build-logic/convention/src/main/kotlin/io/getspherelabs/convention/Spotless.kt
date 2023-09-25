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
        // Workaround for https://github.com/diffplug/spotless/issues/1644
        lineEndings = LineEnding.PLATFORM_NATIVE

        kotlin {
            target("src/**/*.kt")
            ktlint("0.46")
        }

        kotlinGradle {
            target("*.kts")
            ktlint("0.46")
        }
    }

}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
