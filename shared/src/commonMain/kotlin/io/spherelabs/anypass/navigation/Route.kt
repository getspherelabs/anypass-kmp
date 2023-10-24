package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Immutable


@Immutable
sealed interface Route {
    @Immutable
    object Onboarding : Route {
        override fun equals(other: Any?): Boolean {
            return other === Onboarding
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object MasterPassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === MasterPassword
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object Home : Route {
        override fun equals(other: Any?): Boolean {
            return other === Home
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object CreatePassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === CreatePassword
        }

        override fun hashCode(): Int {
            println("Add new password")
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    data class AddNewPassword(val password: String? = null) : Route

    @Immutable
    object SignIn : Route {
        override fun equals(other: Any?): Boolean {
            return other === SignIn
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object SignUp : Route {
        override fun equals(other: Any?): Boolean {
            return other === SignUp
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object Space : Route {
        override fun equals(other: Any?): Boolean {
            return other === Space
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object ChangePassword : Route {
        override fun equals(other: Any?): Boolean {
            return other === ChangePassword
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object NewToken : Route {
        override fun equals(other: Any?): Boolean {
            return other === NewToken
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }

    @Immutable
    object Authenticator : Route {
        override fun equals(other: Any?): Boolean {
            return other === Authenticator
        }

        override fun hashCode(): Int {
            return this::class.simpleName.hashCode()
        }
    }
}
