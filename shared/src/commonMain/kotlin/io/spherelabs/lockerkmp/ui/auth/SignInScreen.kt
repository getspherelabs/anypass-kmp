package io.spherelabs.lockerkmp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.lockerkmp.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordWish
import io.spherelabs.lockerkmp.components.textfield.EmailTextField
import io.spherelabs.lockerkmp.components.textfield.PasswordTextField

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().background(color = Color.White)) {
        Spacer(modifier.height(32.dp))
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = " Hey,\n Login Now!",
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                fontSize = 32.sp,
                color = Color.Black
            )

            Image(
                modifier = modifier.size(125.dp),
                painter = painterResource(MR.images.locker),
                contentDescription = null
            )
        }

        EmailTextField("") {}
        PasswordTextField("") {}
        Spacer(modifier.height(24.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(MR.colors.grey)
            ),
            shape = RoundedCornerShape(24.dp),
            onClick = {

            }) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = fontFamilyResource(
                    fontResource = MR.fonts.googlesans.medium
                )
            )
        }

    }
}