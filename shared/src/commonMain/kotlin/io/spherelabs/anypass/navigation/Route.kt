package io.spherelabs.anypass.navigation

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

    object MasterPassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === MasterPassword
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
            println("Add new password")
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

    object SignIn : Route {
        override fun equals(other: Any?): Boolean {
            return other === SignIn
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object SignUp : Route {
        override fun equals(other: Any?): Boolean {
            return other === SignUp
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    object Space : Route {
        override fun equals(other: Any?): Boolean {
            return other === Space
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }
}
