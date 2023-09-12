package io.spherelabs.data.local.db

import app.cash.sqldelight.ColumnAdapter

object CategoryAdapter: ColumnAdapter<Category, String> {

    override fun decode(databaseValue: String): Category {
        return when(databaseValue) {
            "Social" -> Category.Social
            "Payment" -> Category.Payment
            "Browser" -> Category.Browser
            else -> Category.Unknown
        }
    }

    override fun encode(value: Category): String {
        return when(value) {
            Category.Social -> "Social"
            Category.Browser -> "Browser"
            Category.Payment -> "Payment"
            Category.Unknown -> "Unknown"
        }
    }
}