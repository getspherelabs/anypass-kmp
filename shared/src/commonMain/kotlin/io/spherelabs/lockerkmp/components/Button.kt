package io.spherelabs.lockerkmp.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import io.spherelabs.lockerkmp.MR

@Composable
fun NewItemButton(
    modifier: Modifier = Modifier,
    onNewItemClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(MR.colors.white)
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onNewItemClick.invoke()
        }) {
        Text(
            text = stringResource(MR.strings.new_item),
            color = colorResource(MR.colors.black),
            fontSize = 14.sp,
            fontFamily = fontFamilyResource(MR.fonts.hiraginosans.`gb-w3`),
        )

        Icon(
            modifier = modifier.size(14.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = colorResource(MR.colors.black)
        )

    }
}
