package io.spherelabs.generatepasswordimpl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.slider.LKSlider
import io.spherelabs.designsystem.slider.LKSliderDefaults
import io.spherelabs.foundation.color.Cinderella
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun SpecialSlider(
    modifier: Modifier,
    specialLength: Float,
    onSpecialChanged: (Float) -> Unit,
) {
    val strings = LocalStrings.current
    Column {
        Text(
            modifier = modifier.padding(start = 24.dp),
            text = strings.special,
            fontSize = 12.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.5f),
        )
        LKSlider(
            modifier = modifier.width(100.dp),
            value = specialLength,
            onValueChange = { value, _ ->
                onSpecialChanged.invoke(value)
            },
            colors =
            LKSliderDefaults.sliderColors(
                thumbColor = LavenderBlue.copy(0.7f),
                trackColor = LavenderBlue.copy(0.7f),
                disabledTrackColor = Jaguar,
            ),
            trackHeight = 8.dp,
            valueRange = 0f..10f,
        ) {
            Box(
                modifier =
                Modifier.size(18.dp)
                    .background(
                        color = Cinderella,
                        RoundedCornerShape(8.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "${specialLength.toInt()}",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }

}
