package io.spherelabs.generatepasswordimpl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun RandomPassword(
    modifier: Modifier,
    password: String,
) {

    val strings = LocalStrings.current

    Card(
        modifier =
        modifier
            .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxWidth()
            .height(150.dp),
        backgroundColor = Jaguar,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = modifier.padding(top = 12.dp),
                text = strings.randomPassword,
                fontSize = 14.sp,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(0.3f),
            )
            Spacer(modifier = modifier.height(24.dp))

            Text(
                text = password,
                fontSize = 24.sp,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
