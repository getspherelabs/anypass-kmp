package io.spherelabs.accountimpl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.switch.CupertinoSwitch
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun FingerPrint(
    modifier: Modifier,
    isFingerPrintEnabled: Boolean,
    onCheckedChanged: (isChecked: Boolean) -> Unit,
) {
    val strings = LocalStrings.current
    val dimensions = LocalDimensions.current

    Row(
        modifier =
        modifier
            .padding(horizontal = dimensions.large)
            .fillMaxWidth()
            .height(48.dp)
            .clip(
                RoundedCornerShape(16.dp),
            )
            .background(color = Color(0xff292933)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier.padding(start = dimensions.large),
            text = strings.fingerPrint,
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        CupertinoSwitch(
            isFingerPrintEnabled,
            onCheckedChange = { newChecked ->
                onCheckedChanged.invoke(newChecked)
            },
            modifier = modifier.padding(end = 24.dp),
        )
    }
}
