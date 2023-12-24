package io.spherelabs.accountimpl.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun Logout(
    modifier: Modifier,
    onLogoutClicked: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier =
        modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(48.dp)
            .clip(
                RoundedCornerShape(16.dp),
            )
            .background(color = Color(0xff292933))
            .clickable {
                onLogoutClicked.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier.padding(start = 24.dp),
            text = strings.logout,
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Image(
            modifier = modifier.padding(end = 24.dp).size(20.dp),
            imageVector = Icons.Filled.Logout,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.White),
        )
    }
}
