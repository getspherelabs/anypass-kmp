package io.spherelabs.passphraseimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.button.KeypadType
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.grid.LKGridLayout
import io.spherelabs.designsystem.pininput.LKPinInput
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.passphraseimpl.presentation.MasterPasswordState
import io.spherelabs.passphraseimpl.presentation.MasterPasswordWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun BasicKeyPasswordContent(
    paddingValues: PaddingValues,
    modifier: Modifier,
    state: MasterPasswordState,
    wish: (MasterPasswordWish) -> Unit,
) {
    val strings = LocalStrings.current

    Column(
        modifier = modifier.fillMaxSize().padding(paddingValues),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier.weight(0.5f).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LKPinInput(
                cellColor = Jaguar,
                value = state.password,
                disableKeypad = true,
            ) {
                wish.invoke(MasterPasswordWish.OnPasswordCellChanged(it))
            }
        }

        keypads.forEach { keypad ->
            LKGridLayout(
                modifier = modifier.background(color = BlackRussian),
                items = keypad,
                fontFamily = GoogleSansFontFamily,
            ) { (type, value) ->
                when (type) {
                    KeypadType.Number -> {
                        wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(value))
                    }

                    KeypadType.FingerPrint -> {
                        wish.invoke(MasterPasswordWish.ShowFingerPrint)
                    }

                    KeypadType.Clear -> {
                        wish.invoke(MasterPasswordWish.ClearPassword)
                    }
                }
            }
        }

        Spacer(modifier = modifier.height(32.dp))

        Button(
            modifier =
            modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .height(65.dp)
                .padding(start = 24.dp, end = 24.dp),
            colors =
            ButtonDefaults.buttonColors(
                backgroundColor = LavenderBlue.copy(0.7f),
            ),
            shape = RoundedCornerShape(24.dp),
            onClick = { wish.invoke(MasterPasswordWish.SubmitClicked) },
        ) {
            Text(
                text = strings.submit,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}
