package io.spherelabs.lockerkmp.ui.confirmpassword

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.components.cell.PinInput
import io.spherelabs.lockerkmp.components.grid.GridLayout

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmPassword(
    navigateToHome: () -> Unit

) {

    var pin by remember { mutableStateOf("") }
    val text = remember { mutableStateOf("") }
    val (editValue, setEditValue) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(MR.colors.grey)),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(75.dp))

        // Bordered PIN View
        PinInput(
            value = text.value,
            obscureText = "*",
            length = 4, //Use the number defined here
            disableKeypad = true, //Do not open the android keypad
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
            GridLayout(listOf("1", "2", "3")) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            GridLayout(listOf("4", "5", "6")) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            GridLayout(listOf("7", "8", "9")) { newPin ->
                if (text.value.length < 4) text.value += newPin

            }
            GridLayout(listOf("0")) {

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