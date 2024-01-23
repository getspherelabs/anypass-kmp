package io.spherelabs.homeimpl.ui.component

import androidx.compose.runtime.Stable

val categories = listOf(
    Category(
        id = 1,
        categoryType = CategoryType.Login,
    ),
    Category(
        id = 2,
        categoryType = CategoryType.SecurityNote,
    ),
    Category(
        id = 3,
        categoryType = CategoryType.PersonalInfo,
    ),
)

@Stable
data class Category(
    val id: Int,
    val categoryType: CategoryType,
)

enum class CategoryType(
    val raw: String,
) {
    Login(raw = "Login"),
    SecurityNote(raw = "Security note"),
    PersonalInfo(raw = "Personal info");
}
