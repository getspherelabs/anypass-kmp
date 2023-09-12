package io.spherelabs.lockerkmp.navigation

import androidx.compose.runtime.Immutable


@Immutable
sealed interface Route {
    object Onboarding : Route {
        override fun equals(other: Any?): Boolean {
            return other === Onboarding
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object InputPassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === InputPassword
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object Home : Route {
        override fun equals(other: Any?): Boolean {
            return other === Home
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object CreatePassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === CreatePassword
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object AddNewPassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === AddNewPassword
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }
}
