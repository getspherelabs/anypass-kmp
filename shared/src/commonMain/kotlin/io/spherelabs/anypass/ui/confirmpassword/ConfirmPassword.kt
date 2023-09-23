package io.spherelabs.anypass.ui.confirmpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.sentry.kotlin.multiplatform.Sentry
import io.spherelabs.designsystem.grid.LKGridLayout
import io.spherelabs.designsystem.pininput.LKPinInput
import io.spherelabs.anypass.MR

@Composable
fun ConfirmPassword(
    navigateToHome: () -> Unit
) {

    val text = remember { mutableStateOf("") }

    Sentry.captureMessage("Test")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(MR.colors.grey)),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(75.dp))

        LKPinInput(
            value = text.value,
            disableKeypad = true
        ) {
            text.value = it
        }

        Spacer(modifier = Modifier.height(85.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                .background(color = colorResource(resource = MR.colors.lavender)),
        ) {
            LKGridLayout(
                items = listOf("1", "2", "3"),
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
            ) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            LKGridLayout(
                items = listOf("4", "5", "6"),
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
            ) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            LKGridLayout(
                items = listOf("7", "8", "9"),
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
            ) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            LKGridLayout(
                items = listOf("0"),
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
            ) {

            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(MR.colors.grey)
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = {
                    navigateToHome.invoke()
                }) {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = fontFamilyResource(
                        fontResource = MR.fonts.googlesans.medium
                    )
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}