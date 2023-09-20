package io.spherelabs.lockerkmp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.lockerkmp.MR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.components.textfield.EmailTextField
import io.spherelabs.lockerkmp.components.textfield.PasswordTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier.padding(start = 24.dp).size(56.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(color = Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }

            }
        }
    ) {
        Column(modifier = modifier.fillMaxSize().background(color = Color.White),
            verticalArrangement = Arrangement.Center) {
            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Create a new \naccount",
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    fontSize = 32.sp,
                    color = Color.Black
                )

                Image(
                    modifier = modifier.size(125.dp),
                    painter = painterResource(MR.images.signup),
                    contentDescription = null
                )
            }
            EmailTextField("") {}
            EmailTextField("") {}
            PasswordTextField("") {}
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
                    text = "Register",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = fontFamilyResource(
                        fontResource = MR.fonts.googlesans.medium
                    )
                )
            }

        }
    }
}