package io.spherelabs.homeimpl.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.foundation.color.LavenderBlue

import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.images.MR

@Composable
fun HomeTopBar(
    onOpenClick: () -> Unit,
    navigateToAddPersonalInfo: () -> Unit,
    navigateToAddLogin: () -> Unit,
    navigateToAddSecurityNotes: () -> Unit,
) {
    val strings = LocalStrings.current
    val isExpanded = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        RoundedImage(
            modifier = Modifier.clickable { onOpenClick.invoke() },
            painter = painterResource(MR.images.avatar),
            contentDescription = null,
        )

        Column {
            LKNewItemButton(
                contentText = strings.newItem,
                backgroundColor = LavenderBlue.copy(0.7f),
                contentFontFamily = GoogleSansFontFamily,
            ) {
                isExpanded.value = !isExpanded.value
            }

            CategoryDropDown(
                list = categories,
                isExpanded = isExpanded.value,
                onDismissRequest = {
                    isExpanded.value = false
                },
                onCategoryClick = { type ->
                    when (type) {
                        CategoryType.Login -> navigateToAddLogin()
                        CategoryType.SecurityNote -> navigateToAddSecurityNotes()
                        CategoryType.PersonalInfo -> navigateToAddPersonalInfo()
                    }
                },
            )
        }

    }

}
