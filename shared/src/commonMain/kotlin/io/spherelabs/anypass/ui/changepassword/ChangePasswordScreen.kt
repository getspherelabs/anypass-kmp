package io.spherelabs.anypass.ui.changepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.authpresentation.signup.SignUpWish
import io.spherelabs.designsystem.textfield.KeyPasswordTextField
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun ChangePasswordRoute(

) {

}

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { newPaddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(paddingValues = newPaddingValues),
        ) {
//            KeyPasswordTextField(
//                textValue = state.keyPassword,
//                passwordVisibility = state.isKeyPasswordVisibility,
//                description = strings.keyPasswordRequirement,
//                fontFamily = GoogleSansFontFamily,
//                onToggleChanged = {
//                    wish.invoke(SignUpWish.ToggleKeyPasswordVisibility)
//                },
//                onValueChanged = { newValue ->
//                    wish.invoke(SignUpWish.OnKeyPasswordChanged(newValue))
//                },
//            )
//
//            KeyPasswordTextField(
//                title = "Confirm a key password",
//                description = strings.keyPasswordRequirement,
//                textValue = state.confirmKeyPassword,
//                passwordVisibility = state.isConfirmKeyPasswordVisibility,
//                fontFamily = GoogleSansFontFamily,
//                onToggleChanged = {
//                    wish.invoke(SignUpWish.ToggleConfirmKeyPasswordVisibility)
//                },
//                onValueChanged = { newValue ->
//                    wish.invoke(SignUpWish.OnConfirmKeyPasswordChanged(newValue))
//                },
//            )
//
//            if (state.isKeyPasswordSame) {
//                Text(
//                    modifier = modifier.padding(start = 24.dp, top = 4.dp),
//                    text = strings.passwordSameFailure,
//                    color = Color.White.copy(alpha = 0.7f),
//                    fontFamily = GoogleSansFontFamily,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 12.sp,
//                )
//            }
            Spacer(modifier.height(24.dp))
        }
    }
}
