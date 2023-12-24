package domain.model

sealed interface PasswordType {
  object Weak : PasswordType

  object Strong : PasswordType

  object Reused : PasswordType
}
