package io.spherelabs.homeimpl.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun CategoryDropDown(
    list: List<Category>,
    isExpanded: Boolean,
    onCategoryClick: (type: CategoryType) -> Unit,
    onDismissRequest: () -> Unit,
) {
    DropdownMenu(
        modifier = Modifier.wrapContentSize(),
        expanded = isExpanded,
        onDismissRequest = { onDismissRequest.invoke() },
    ) {
        list.forEach {
            val categoryType = when (it.categoryType) {
                CategoryType.Login -> Icons.Default.Login
                CategoryType.SecurityNote -> Icons.Default.Security
                CategoryType.PersonalInfo -> Icons.Default.Badge
            }

            DropdownMenuItem(
                onClick = {
                    onCategoryClick.invoke(it.categoryType)
                },
                content = {
                    Text(
                        text = it.categoryType.raw,
                        fontSize = 14.sp,
                        fontFamily = GoogleSansFontFamily,
                        color = Jaguar,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = categoryType, contentDescription = null, tint = Jaguar)
                },
            )
        }

    }
}
